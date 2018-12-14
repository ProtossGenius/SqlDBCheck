var CheckCtrl = Java.type("com.suremoon.ctrl.CheckCtrl");
var ctrl = CheckCtrl.getCheckCtrl();
var DBLoader = Java.type("com.suremoon.db_about.DBLoader");

function doCheck(lineData, db) {
    uid = lineData.getString("uid");
    id = lineData.getString("id");
    /*
     *    The part that needs to be parsed is parsed here, and then the following method is used to determine whether it
     * exists. If it does not exist, it will be written to the log file.
     */
    db.checkStringVal("user", "id", uid);
    db.checkStringVal("items", "id", id);
}


