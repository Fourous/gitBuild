package main.java.com.fourous.gitbuild.base.controller;

import main.java.com.fourous.gitbuild.base.vo.MsgResponse;
import main.java.com.fourous.gitbuild.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: Controller基类
 */
public class BaseController {
    private static final Logger log = Logger.getLogger(BaseController.class);
    public static final int CODE_OK = 0;
    public static final int CODE_ERROR = 1;
    private static final String contentType_default = "application/json";

    /**
     * CreateMsgResponse
     *
     * @param code
     * @param msg
     * @return
     */
    protected final MsgResponse createMsgResponese(int code, String msg) {
        return new MsgResponse(code, msg);
    }

    protected final MsgResponse createMsgResponese() {
        return new MsgResponse();
    }

    protected final MsgResponse createMsgResponseOk() {
        MsgResponse msgResponse = new MsgResponse(CODE_OK, "");
        return msgResponse;
    }

    protected final void writeResponse(HttpServletResponse response, MsgResponse msgResponse) {
        if (response == null) {
            log.warn("response is null");
        }
        if (msgResponse == null) {
            log.warn("msgResponse is null");
        }
        String content = JsonUtil.toJson(msgResponse);
        writeContent(response, content);
    }

    protected void writeContent(HttpServletResponse response, String content) {
        if (response == null) {
            log.warn("response is null");
        }
        PrintWriter printWriter = null;
        try {
            response.setContentType(contentType_default);
            printWriter = response.getWriter();
            printWriter.write(content);
            printWriter.flush();
        } catch (Exception e) {
            log.error("write error");
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    protected void output(HttpServletResponse response, MsgResponse msgResponse) {
        writeResponse(response, msgResponse);
    }

    protected void output(HttpServletResponse response, Map pair) {
        String content = JsonUtil.toJson(pair);
        writeContent(response, content);
    }

    protected void outputFile(HttpServletResponse response, File file) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        while (true) {
            int bytesRead;
            if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) {
                break;
            }
            bos.write(buff, 0, bytesRead);
        }
        file.deleteOnExit();
        if (bis != null) {
            bis.close();
        }
        if (bos != null) {
            bos.close();
        }
    }

    @ExceptionHandler
    @ResponseBody
    public MsgResponse handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        String websocketId = request.getParameter("websocketId");
        if (websocketId != null) {
            main.java.com.fourous.gitbuild.base.log.Logger.error(e.getMessage());
        }
        return MsgResponse.error(e.getMessage());
    }
}
