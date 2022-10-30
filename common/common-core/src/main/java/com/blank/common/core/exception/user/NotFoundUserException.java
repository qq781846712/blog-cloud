package com.blank.common.core.exception.user;

public class NotFoundUserException extends UserException {
    private static final long serialVersionUID = 1L;

    public NotFoundUserException() {
        super("未找到该用户！");
    }
}
