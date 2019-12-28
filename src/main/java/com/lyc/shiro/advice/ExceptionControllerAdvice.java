package com.lyc.shiro.advice;



import com.lyc.shiro.common.CommonResult;
import com.lyc.shiro.common.CommonResultResponse;
import com.lyc.shiro.constant.Codes;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局处理异常 信息
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public CommonResult methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		StringBuilder sb = getStringBuilder(exception);
		return CommonResultResponse.fail(sb.toString());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public CommonResult IllegalArgumentException(IllegalArgumentException exception) {
		return CommonResultResponse.fail(exception.toString());
	}

	private StringBuilder getStringBuilder(MethodArgumentNotValidException exception) {
		StringBuilder sb = getExceptionMsg(exception.getBindingResult());
		return sb;
	}

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public CommonResult bindException(BindException exception) {
		StringBuilder sb = getExceptionMsg(exception.getBindingResult());
		return CommonResultResponse.fail(sb.toString());
	}

	private StringBuilder getExceptionMsg(BindingResult bindingResult) {
		BindingResult br = bindingResult;
		List<FieldError> fieldErrors = br.getFieldErrors();
		StringBuilder sb = new StringBuilder();
		for (FieldError fieldError : fieldErrors) {
			if (sb.length() > 0) {
				sb.append("，");
			}
			sb.append(fieldError.getDefaultMessage());
		}
		return sb;
	}


	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public CommonResult constraintViolationException(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<?> cv : constraintViolations) {
			if (sb.length() > 0) {
				sb.append("，");
			}
			sb.append(cv.getMessage());
		}
		return CommonResultResponse.fail(sb.toString());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public CommonResult fieldException(HttpRequestMethodNotSupportedException exception) {
		return CommonResultResponse.fail("不支持"+exception.getMethod()+"请求");
	}

	/**
	 * shrio
	 * ==========================================================================================
	 */

	//不满足@RequiresGuest注解时抛出的异常信息
	private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public CommonResult handleShiroException(ShiroException e) {
		String eName = e.getClass().getSimpleName();
		LOGGER.error("shiro执行出错：{}",eName);
		return CommonResultResponse.fail("服务器忙请稍后再试！");
	}

	@ExceptionHandler(UnauthenticatedException.class)
	@ResponseBody
	public CommonResult page401(UnauthenticatedException e) {
		String eMsg = e.getMessage();
		if (StringUtils.startsWithIgnoreCase(eMsg,GUEST_ONLY)){
			return CommonResultResponse.fail(Codes.UNAUTHEN,"只允许游客访问，若您已登录，请先退出登录");
		}
		return CommonResultResponse.fail(Codes.UNAUTHEN,"用户没有登入");
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public CommonResult page403() {
		return CommonResultResponse.fail(Codes.UNAUTHZ,"用户没有该权限");
	}

	/**
	 * shrio
	 * ==========================================================================================
	 * @return
	 */

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public CommonResult allExceptionHandler(Exception exception, HttpServletRequest request) {
		LOGGER.error(exception.getMessage(), exception);
		return CommonResultResponse.fail("服务器忙请稍后再试！");
	}

}
