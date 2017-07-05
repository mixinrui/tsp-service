package com.boxfish.tsp.common.exception;


import com.boxfish.tsp.common.JsonResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.boxfish.tsp.common.JsonResultModel.newJsonResultModel;

@ControllerAdvice
public class BoxfishExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public final Object processAllException(Exception e) {
        final JsonResultModel jsonResultModel = newJsonResultModel("ERROR");
        jsonResultModel.setReturnCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        jsonResultModel.setReturnMsg(e.getCause().getClass().getName());

        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResultModel);
    }

    /**
     * 网络异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NetWorkException.class)
    public Object networkException(NetWorkException e) {
        return boxfishExceptionReturn(e);
    }

    /**
     * 校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ValidationException.class)
    public Object validationException(ValidationException e) {
        return boxfishExceptionReturn(e);
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object businessException(BusinessException e) {
        return boxfishExceptionReturn(e);
    }

    /**
     * 认证异常,拒绝访问
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public Object unauthorizedException(UnauthorizedException e) {
        return boxfishExceptionReturn(e);
    }

    private Object boxfishExceptionReturn(BoxfishException e) {
        JsonResultModel jsonResultModel = newJsonResultModel();
        jsonResultModel.setReturnCode(e.getReturnCode());
        jsonResultModel.setReturnMsg(e.getReturnMsg());
        e.printStackTrace();
        return ResponseEntity.status(e.getReturnCode()).body(jsonResultModel);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Object httpMessageNotReadableException(HttpMessageNotReadableException e) {
        JsonResultModel resultModel = newJsonResultModel();
        resultModel.setData("提交的数据内容包含错误的类型或格式,请检查并重试!");
        resultModel.setReturnCode(HttpStatus.BAD_REQUEST.value());
        resultModel.setReturnMsg(e.getCause().getClass().getName());

        return ResponseEntity.badRequest().body(resultModel);
    }
}
