package com.zhitar.topjavagraduation.util;

import com.zhitar.topjavagraduation.interfaces.HasId;
import com.zhitar.topjavagraduation.util.exception.IllegalRequestDataException;
import com.zhitar.topjavagraduation.util.exception.NotFoundException;

import java.io.Serializable;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, Long id) {
        return checkNotFound(object, "id=" + id + " not found");
    }

    public static void checkNotFoundWithId(boolean found, Long id) {
        checkNotFound(found, "id=" + id + " not found");
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static <T extends Serializable> void assureIdConsistent(HasId<T> bean, T id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (bean.isNew()) {
            bean.setId(id);
        } else if (!bean.getId().equals(id)) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
//    public static Throwable getRootCause(Throwable t) {
//        Throwable result = t;
//        Throwable cause;
//
//        while (null != (cause = result.getCause()) && (result != cause)) {
//            result = cause;
//        }
//        return result;
//    }
}