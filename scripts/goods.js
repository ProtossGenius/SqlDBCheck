
function doCheck(lineData, db) {
    des = lineData.getString("des");
    id = lineData.getString("id");
    db.checkStringVal("goods", "id", id);
    goodsList = des.split(";");
    goodsList.forEach(function (goods) {
        itemid = goods.split("|")[0];
        db.checkStringVal("items", "id", itemid);
    });
}
