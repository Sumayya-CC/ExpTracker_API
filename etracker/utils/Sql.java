package com.example.etracker.utils;

public interface Sql {

	public interface MetadataSql{
		
		 final String TOTAL_BAR_YEAR = "SELECT SUM(CASE WHEN TRANSACTION_TYPE=0 THEN amount END) AS TotalExpense, SUM(CASE WHEN TRANSACTION_TYPE=1 THEN amount END) AS TotalIncome FROM T_TRANSACTION WHERE year(TRANSACTION_DATE) = year( current_date()) and USER_ID= ? ";
		 final String TOTAL_BAR_MONTH = "SELECT SUM(CASE WHEN TRANSACTION_TYPE=0 THEN amount END) AS TotalExpense, SUM(CASE WHEN TRANSACTION_TYPE=1 THEN amount END) AS TotalIncome FROM T_TRANSACTION WHERE year(TRANSACTION_DATE) = year( current_date()) and month(TRANSACTION_DATE) = month(current_date())and USER_ID=?";
		 final String TOTAL_LINE_YEAR = "SELECT monthname(TRANSACTION_DATE) as Day, SUM(CASE WHEN TRANSACTION_TYPE=0 THEN amount END) AS TotalExpense, SUM(CASE WHEN TRANSACTION_TYPE=1 THEN amount END) AS TotalIncome FROM T_TRANSACTION WHERE year(TRANSACTION_DATE) = year(current_date()) and USER_ID=? AND TRANSACTION_TYPE =0 GROUP BY Day, month(TRANSACTION_DATE) ORDER BY month(TRANSACTION_DATE)";
		 final String TOTAL_LINE_MONTH = "SELECT dayofmonth(TRANSACTION_DATE) as Day, SUM(CASE WHEN TRANSACTION_TYPE=0 THEN amount END) AS TotalExpense, SUM(CASE WHEN TRANSACTION_TYPE=1 THEN amount END) AS TotalIncome FROM T_TRANSACTION WHERE (month(TRANSACTION_DATE) = month(current_date()) and year(TRANSACTION_DATE) = year(current_date()) and USER_ID=? and TRANSACTION_TYPE =0) GROUP BY dayofmonth(TRANSACTION_DATE)";
		 final String ADDINCOME = "INSERT INTO T_TRANSACTION(TRANSACTION_TYPE,USER_ID,ITEM,CATEGORY_ID,AMOUNT,TRANSACTION_DATE) VALUES (1,?,?,?,?,?)";
		 final String ADDEXPENSE = "INSERT INTO T_TRANSACTION(TRANSACTION_TYPE,USER_ID,ITEM,CATEGORY_ID,AMOUNT,TRANSACTION_DATE) VALUES (0,?,?,?,?,?)";
		 final String ADDINCOMECATEGORY = "INSERT INTO T_CATEGORY(TRANSACTION_TYPE,CATEGORY_NAME,USER_ID) VALUES (1,?,?)";
		 final String ADDEXPENSECATEGORY = "INSERT INTO T_CATEGORY(TRANSACTION_TYPE,CATEGORY_NAME,USER_ID) VALUES (0,?,?)";
		 final String CATEGORY_BAR_MONTH = "SELECT C.CATEGORY_NAME, SUM(AMOUNT) SUMAMOUNT FROM T_TRANSACTION T INNER JOIN T_CATEGORY C ON T.CATEGORY_ID = C.ID WHERE T.TRANSACTION_TYPE = 0 AND T.USER_ID = ? AND MONTH(T.TRANSACTION_DATE) = month(current_date()) AND YEAR(T.TRANSACTION_DATE) = year(current_date()) GROUP BY C.CATEGORY_NAME ORDER BY SUMAMOUNT DESC"; 
		 final String CATEGORY_BAR_YEAR = "SELECT C.CATEGORY_NAME, SUM(AMOUNT) SUMAMOUNT FROM T_TRANSACTION T INNER JOIN T_CATEGORY C ON T.CATEGORY_ID = C.ID WHERE T.TRANSACTION_TYPE = 0 AND T.USER_ID = ? AND YEAR(T.TRANSACTION_DATE) = year(current_date()) GROUP BY C.CATEGORY_NAME ORDER BY SUMAMOUNT DESC"; 

		 final String LIST_CATEGORY_EXPENSE = "SELECT * FROM T_CATEGORY WHERE TRANSACTION_TYPE = 0 AND USER_ID=?";
		 final String LIST_CATEGORY_INCOME = "SELECT * FROM T_CATEGORY WHERE TRANSACTION_TYPE = 1 AND USER_ID=?";
		 final String FETCH_INCOME = "SELECT T_TRANSACTION.TRANSACTION_TYPE AS TYPE, T_TRANSACTION.ITEM AS ITEM,T_TRANSACTION.AMOUNT AS AMOUNT, T_TRANSACTION.TRANSACTION_DATE AS DATE, T_CATEGORY.CATEGORY_NAME AS CATEGORY FROM T_TRANSACTION JOIN T_CATEGORY ON T_TRANSACTION.CATEGORY_ID=T_CATEGORY.ID WHERE T_TRANSACTION.TRANSACTION_TYPE=1 AND T_TRANSACTION.USER_ID= ? ORDER BY DATE DESC, T_TRANSACTION.ID DESC ";
		 final String FETCH_EXPENSE = "SELECT T_TRANSACTION.TRANSACTION_TYPE AS TYPE, T_TRANSACTION.ITEM AS ITEM,T_TRANSACTION.AMOUNT AS AMOUNT, T_TRANSACTION.TRANSACTION_DATE AS DATE, T_CATEGORY.CATEGORY_NAME AS CATEGORY FROM T_TRANSACTION JOIN T_CATEGORY ON T_TRANSACTION.CATEGORY_ID=T_CATEGORY.ID WHERE T_TRANSACTION.TRANSACTION_TYPE=0 AND T_TRANSACTION.USER_ID= ? ORDER BY DATE DESC, T_TRANSACTION.ID DESC";
		 final String FETCH_INCOME_EXPENSE = "SELECT T_TRANSACTION.TRANSACTION_TYPE AS TYPE, T_TRANSACTION.ITEM AS ITEM,T_TRANSACTION.AMOUNT AS AMOUNT, T_TRANSACTION.TRANSACTION_DATE AS DATE, T_CATEGORY.CATEGORY_NAME AS CATEGORY FROM T_TRANSACTION JOIN T_CATEGORY ON T_TRANSACTION.CATEGORY_ID=T_CATEGORY.ID WHERE T_TRANSACTION.USER_ID= ? ORDER BY DATE DESC, T_TRANSACTION.ID DESC";
		 
		 final String UPDATE_PASSWORD = "UPDATE T_USER SET PASSWORD=? WHERE EMAILID=?";
		 final String ADD_USER = "INSERT INTO T_USER (EMAILID, NAME,PASSWORD) VALUES(?,?,?)";
		 final String CHECK_USER = "SELECT EXISTS(SELECT * from T_USER WHERE EMAILID=?)";
		 final String GET_PASSWORD = "SELECT PASSWORD FROM T_USER WHERE EMAILID=?";
		 final String FETCH_USER = "SELECT ID,NAME FROM T_USER WHERE EMAILID=?";
		 

	}
}
