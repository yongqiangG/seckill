package com.johnny.exception;
/*通用异常*/
public class SecKillException extends RuntimeException{
    public SecKillException(String message){
        super(message);
    }
    public SecKillException(String message,Throwable cause){
        super(message, cause);
    }
}
