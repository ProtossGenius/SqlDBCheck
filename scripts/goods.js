
function doCheck(lineData, checker) {
    des = lineData.getString("des");
    id = lineData.getString("id");
    checker.singleValueCheck("goods", "id", id);
    goodsList = des.split(";");
    goodsList.forEach(function (goods) {
        itemid = goods.split("|")[0];
        checker.singleValueCheck("items", "id", itemid);
    });
}
