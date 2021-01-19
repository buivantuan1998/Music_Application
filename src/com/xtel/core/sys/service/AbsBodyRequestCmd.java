package com.xtel.core.sys.service;

import com.xtel.core.common.BaseResponse;
import com.xtel.core.common.ErrorTypes;
import com.xtel.core.validator.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;

public abstract class AbsBodyRequestCmd extends AbsApiBaseCmd {

    public AbsBodyRequestCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    public void execute() {

        logger.debug(String.format("transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s",
                transid, channel, logRequest(), jsonRequest, classRequest));

        try {
            objRequest = gson.fromJson(jsonRequest, classRequest);
            if (objRequest == null) {
                setResponse(ErrorTypes.REQUEST_INVALID);
            }
        } catch (Exception e) {
            logger.warn(String.format("transid: %s, Common Request Invalid !", transid), e);
            createResponse();
            return;
        }

        logger.debug(String.format("BEGIN_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s",
                transid, channel, logRequest(), jsonRequest, classRequest));
        try {
            if (!isValidToken()) {
                if (objResponse == null) {
                    setResponse(ErrorTypes.TOKEN_INVALID);
                }
                createResponse();
                return;
            }

            if (!isValidData()) {
                if (objResponse == null) {
                    setResponse(ErrorTypes.REQUEST_INVALID);
                }
                createResponse();
                return;
            }

            executeCmd();
        } catch (Exception e) {
            if (objResponse == null) {
                setResponse(ErrorTypes.UNKNOWN);
            }
            logger.warn(String.format("transid: %s, channel: %s, request: %s, exception: %s",
                    transid, channel, logRequest(), e.getMessage()), e);
        }

        createResponse();
    }


    @Override
    protected boolean isValidData() {
        Class<?> clazz = objRequest.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            NotNull notnull = field.getAnnotation(NotNull.class);
            NotNullOrEmpty notNullOrEmpty = field.getAnnotation(NotNullOrEmpty.class);
            Length length = field.getAnnotation(Length.class);
            IsEmail isEmail = field.getAnnotation(IsEmail.class);

            Object value = null;
            try {
                value = field.get(objRequest);
            } catch (Exception e) {
                logger.warn(String.format("transid: %s, validate data throw excetion !", transid), e);
                return false;
            }

            if (notnull != null) {
                if (value == null) {
                    setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s is NULL !", field.getName()));
                    return false;
                }
            }

            if (notNullOrEmpty != null) {
                try {
                    if (value == null) {
                        setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s is NULL or EMPTY !", field.getName()));
                        return false;
                    } else if (value instanceof String) {
                        if (((String) value).trim().isEmpty()) {
                            setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s is NULL or EMPTY !", field.getName()));
                            return false;
                        }
                    } else if (value instanceof Number) {
                        if (((Number) value).equals(0L)) {
                            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s is NULL or EMPTY !", field.getName()));
                            return false;
                        }
                    } else if (value instanceof Collection) {
                        if (((Collection) value).isEmpty()) {
                            setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s is NULL or EMPTY !", field.getName()));
                            return false;
                        }
                    } else if (value instanceof Object[]) {
                        if (((Object[]) ((Object[]) value)).length == 0) {
                            setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s is NULL or EMPTY !", field.getName()));
                            return false;
                        }
                    }
                } catch (Exception e) {
                    setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s", e.getMessage()));
                    return false;
                }
            }

            if (length != null) {
                double lengthOfObject = ValidateUtils.getLength(value);
                double equal = length.equal();
                double lessThan = length.lessThan();
                double greatThan = length.greatThan();
                double lessThanOrEqual = length.lessThanOrEqual();
                double greatThanOrEqual = length.greatThanOrEqual();

                if (equal != -1) {
                    if (lengthOfObject != equal) {
                        setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("Length of %s is not equal %s", field.getName(), equal));
                        return false;
                    }
                }

                if (lessThan != -1) {
                    if (lengthOfObject >= lessThan) {
                        setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("Length of %s is not less than %s", field.getName(), equal));
                        return false;
                    }
                }

                if (greatThan != -1) {
                    if (lengthOfObject <= lessThan) {
                        setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("Length of %s is not great than %s", field.getName(), equal));
                        return false;
                    }
                }

                if (lessThanOrEqual != -1) {
                    if (lengthOfObject > lessThanOrEqual) {
                        setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("Length of %s is not less than or equal %s", field.getName(), equal));
                        return false;
                    }
                }

                if (greatThanOrEqual != -1) {
                    if (lengthOfObject < lessThanOrEqual) {
                        setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("Length of %s is not great than or equal %s", field.getName(), equal));
                        return false;
                    }
                }
            }

            if(isEmail != null){
                if(!ValidateUtils.isValidEmail((String) value)) {
                    setResponse(ErrorTypes.REQUEST_INVALID.getCode(), String.format("%s NOT is VALID email !", field.getName()));
                    return false;
                }
            }

            field.setAccessible(accessible);

        }
        return true;
    }
}
