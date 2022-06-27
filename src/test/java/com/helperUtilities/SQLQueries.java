package com.helperUtilities;

public class SQLQueries {


    public static String CREATE_TABLE_SQL_QUERY = "CREATE TABLE schema1.Product (product_id INT NOT NULL,product_name VARCHAR(100) NOT NULL,product_manufacturer VARCHAR(40) NOT NULL,PRIMARY KEY (product_id))";
    public static String TABLE_VALIDATION_SQL_QUERY = "select Count(*) from schema1.Product";
    public static String INSERT_VALUES_SQL_QUERY = "INSERT INTO Product (product_id, product_name,product_manufacturer) VALUES " +
            "(1,'Product1','TA9'), " +
            "(2,'Product2','ML3'), " +
            "(3,'Product3','CI7'), " +
            "(4,'Product4','LD3'), " +
            "(5,'Product5','UG3'), " +
            "(6,'Product6','GD4') ";

    public static String INSERT_VALUES_SQL_QUERY_ORACLE = "INSERT ALL" +
            "INTO schema1.Product (PRODUCT_ID,PRODUCT_NAME,PRODUCT_MANUFACTURER) values (2,'Product2', 'ty2')" +
            "INTO schema1.Product (PRODUCT_ID,PRODUCT_NAME,PRODUCT_MANUFACTURER) values (3,'Product3', 'ty3')" +
            "SELECT * FROM schema1.Product";
}