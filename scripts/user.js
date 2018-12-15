/**
 * This demonstrates how to safely use Statement for custom statement queries. This way you don't have to worry about
 * the problem that Statement forgets to close.
 * @param lineData
 * @param checker
 */

function doCheck(lineData, checker) {
    id = lineData.getString("id");
    nums = 0;
    checker.sqlAct(function (stat) {
        sql = "select count(*) from`user` where id ='" + id + "';";
        resultSet = stat.executeQuery(sql);
        nums = resultSet.getInt(1);
        resultSet.close();
    });
    switch (nums) {
        case 1:
            return;
        case 0:
            checker.writeLog(String.format("[DATA_NOT_FOUND] when check table [%s].Data missing in table[%s] columnName[%s] value[%s]", dealingTable, tableName, colName, value));
        default:
            checker.writeLog(String.format("[DATA_MORE_THAN_ONE] when check table [%s].Data missing in table[%s] columnName[%s] value[%s] nums %d", dealingTable, tableName, colName, value, nums));
    }
}
