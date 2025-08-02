/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.utils;

/**
 *
 * @author javaugi
 */
public class RegexConstant {
    public static final String REGEX_CSV = "\\s*,\\s*";
    public static final String REGEX_CSV_2 = "[,\\s]+";
    public static final String REGEX_CSV_FULL = "\\\\t*\\s*,(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
}
