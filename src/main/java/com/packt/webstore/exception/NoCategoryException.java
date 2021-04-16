package com.packt.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="이 범주 상품은 없습니다.")
public class NoCategoryException extends RuntimeException {
	private static final long serialVersionUID = 4505752430771486707L;
}
