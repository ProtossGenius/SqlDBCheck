//I probably can't use it here, just hint that there is such a usage, you can use the java class directly, this is the coolest place I think.
// var CheckCtrl = Java.type("com.suremoon.ctrl.CheckCtrl");
// var ctrl = CheckCtrl.getCheckCtrl();
// var DBLoader = Java.type("com.suremoon.db_about.DBLoader");

function doCheck(lineData, checker) {
    uid = lineData.getString("uid");
    id = lineData.getString("id");
    /*
     *    The part that needs to be parsed is parsed here, and then the following method is used to determine whether it
     * exists. If it does not exist, it will be written to the log file.
     */
    checker.checkStringVal("user", "id", uid);//When there is only one primary key
    checker.checkValExist("items", "`id`='" + id + "'");//When there are multiple primary keys or need a special query
}


