
function doCheck(lineData, checker) {
    des = lineData.getString("des");
    id = lineData.getString("id");
    checker.checkStringVal("goods", "id", id);
    goodsList = des.split(";");
    goodsList.forEach(function (goods) {
        itemid = goods.split("|")[0];
        checker.checkStringVal("items", "id", itemid);
    });
}
