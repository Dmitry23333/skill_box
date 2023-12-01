package org.example.utils;

public class Constants {
    public final static String FULL_NAME_REGEX = "^[А-Я][а-я]{1,23}+\\s[А-Я][а-я]{1,23}+\\s[А-Я][а-я]{1,23}+$";
    public final static String PHONE_NUMBER_REGEX = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";

    public final static String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public final static String FULL_NAME_ERROR_MESSAGE = "Введите ФИО снова";
    public final static String PHONE_NUMBER_ERROR_MESSAGE = "Введите номер телефона снова";
    public final static String EMAIL_ERROR_MESSAGE = "Введите email снова";

    public final static String FULL_NAME_INPUT_MESSAGE = "Введите ФИО";
    public final static String PHONE_NUMBER_INPUT_MESSAGE = "Введите номер телефона";
    public final static String EMAIL_INPUT_MESSAGE = "Введите email";
    public final static String MESSAGE_OF_SAVED = "Введите email";
    public final static String INIT_MESSAGE = "Init file is disable";
    public final static String  DELIMITER  = ";";
    public final static String  FILE_NOT_FOUND  = ";";
}
