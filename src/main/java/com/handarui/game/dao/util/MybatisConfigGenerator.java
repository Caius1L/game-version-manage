package com.handarui.game.dao.util;

import java.sql.*;

/**
 * 生成mybatis generatorConfig.xml里的context格式输出
 *
 * @author zhenggaojie 2015-09-18.
 */
public class MybatisConfigGenerator {

    private String URL = "jdbc:mysql://dev.jingzheit.com:3306/qa_fun?characterEncoding=utf-8&amp;useSSL=false";

    private String USER = "root";
    private String PASSWORD = "Tryme!23";

    public MybatisConfigGenerator(String URL, String USER, String PASSWORD) {
        this.PASSWORD = PASSWORD;
        this.URL = URL;
        this.USER = USER;
    }

    private MybatisConfigGenerator() {
    }

    public static void main(String[] args) {
        try {
            String schemaName = "game_number";
            String tableName = "role_permission";
            new MybatisConfigGenerator().generate(schemaName, tableName);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generate(String schemaName, String tableName) throws SQLException,
            ClassNotFoundException {
        generate(schemaName, tableName, convertToClassNomi(tableName) + "Do");
    }

    private void generate(String schemaName, String tableName, String doName) throws SQLException,
            ClassNotFoundException {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT col.COLUMN_NAME , col.DATA_TYPE FROM information_schema.COLUMNS col WHERE LOWER(col.TABLE_SCHEMA) = '"
                    + schemaName + "' AND LOWER(col.TABLE_NAME) in ('" + tableName + "')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println("<table tableName=\"" + tableName + "\" domainObjectName=\"" + doName
                    + "\" enableCountByExample=\"false\">");
//            System.out.println("<property name=\"runtimeSchema\" value=\"" + schemaName + "\"/>");
            int index = 0;
            while (resultSet.next()) {

                String columnName = resultSet.getString(1);
                String dataType = resultSet.getString(2);
                if (index++ == 0) {
                    System.out.println("<generatedKey column=\"" + columnName
                            + "\" sqlStatement=\"MySql\" identity=\"true\"/>");
                }
                if (!("varchar".equals(dataType) || "text".equals(dataType))) {
                    System.out.println(" <columnOverride column=\"" + columnName + "\" property=\"" + convertToCamel(columnName)
                            + "\" javaType=\"" + dob(dataType) + "\" jdbcType=\"" + doc(dataType) + "\"/>");
                }
            }
            System.out.println("</table>");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private String doc(String dataType) {
        switch (dataType) {
            case "int":
                return "INTEGER";
            case "mediumint":
                return "INTEGER";
            case "enum":
                return "VARCHAR";
            case "datetime":
                return "TIMESTAMP";
            default:
                return dataType.toUpperCase();
        }
    }

    private String dob(String dataType) {
        switch (dataType) {
            case "decimal":
                return "java.lang.Double";
            case "bigint":
                return "java.lang.Long";
            case "int":
                return "java.lang.Long";
            case "smallint":
                return "java.lang.Integer";
            case "tinyint":
                return "java.lang.Integer";
            case "mediumint":
                return "java.lang.Long";
            case "enum":
                return "java.lang.Integer";
            case "date":
                return "java.util.Date";
            case "datetime":
                return "java.util.Date";
            case "timestamp":
                return "java.util.Date";
            default:
                return dataType;
        }
    }

    private String convertToCamel(String columnName) {
        StringBuilder buf = new StringBuilder();
        String[] split = columnName.split("_");
        if (split.length <= 1) {
            return columnName;
        }
        for (int j = 0; j < split.length; j++) {
            if (j == 0) {
                buf.append(split[j]);
                continue;
            }
            String s = split[j];
            buf.append(String.valueOf(Character.toUpperCase(s.charAt(0)))).append(s.substring(1));
        }
        return buf.toString();
    }

    private String convertToClassNomi(String columnName) {
        String s = convertToCamel(columnName);
        char[] chars = s.toCharArray();
        char aChar = chars[0];
        chars[0] = Character.toUpperCase(aChar);
        s = new String(chars);
        return s;
    }
}
