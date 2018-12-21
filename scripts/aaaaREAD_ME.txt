function doCheck(lineData, checker) {
    //Frequently used method
    id = lineData.getString(str)
    checker.singleValueCheck("goods", "id", id);
    checker.conditionCheck("items", "`id`='" + id + "'")
	checker.sqlAct(function (stat) {
        sql = "select count(*) from`user` where id ='" + id + "';";
        resultSet = stat.executeQuery(sql);
        nums = resultSet.getInt(1);
        switch (nums) {
            case 1:
                return;
            case 0:
                checker.writeLog(String.format("[DATA_NOT_FOUND] when check table [%s].Data missing in table[%s] columnName[%s] value[%s]", dealingTable, tableName, colName, value));
            default:
                checker.writeLog(String.format("[DATA_MORE_THAN_ONE] when check table [%s].Data missing in table[%s] columnName[%s] value[%s] nums %d", dealingTable, tableName, colName, value, nums));
        }
        resultSet.close();
    });
    checker.safeQuery(sql, function (resultSet) {
        nums = resultSet.getInt(1);
        switch (nums) {
            case 1:
                return;
            case 0:
                checker.writeLog(String.format("[DATA_NOT_FOUND] when check table [%s].Data missing in table[%s] columnName[%s] value[%s]", dealingTable, tableName, colName, value));
            default:
                checker.writeLog(String.format("[DATA_MORE_THAN_ONE] when check table [%s].Data missing in table[%s] columnName[%s] value[%s] nums %d", dealingTable, tableName, colName, value, nums));
        }
    })
}
