var request = new XMLHttpRequest();
var coinContainer = document.getElementById("coin-data");

request.open('GET', "https://api.coinmarketcap.com/v1/ticker/", false);
request.send();
var data = JSON.parse(request.response);
document.getElementById("coin-data").innerHTML = buildTable(data);

//renderHTML(data);

function renderHTML(data) {

    var coinString = "";
    for (var i = 0; i < data.length;i++) {
        coinString += "<p>" + data[i].name + " $" + data[i].price_usd + "</p>"
    }
    coinContainer.insertAdjacentHTML('beforeend', coinString)
}

function buildTable(data) {
    var result = "";
    result += "<table>\
                <tr>\
                    <th>Rank</th>\
                    <th>Symbol</th>\
                    <th>Name</th>\
                    <th>Supply</th>\
                    <th>Market Cap</th>\
                    <th>Price</th>\
                    <th>Change(24h)</th>\
                </tr>";

    for (var i = 0; i < data.length;i++) {
        result += "<tr>\
                    <td>" + data[i].rank + "</td>" +
                    "<td>" + data[i].symbol + "</td>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td>" + data[i].total_supply + "</td>" +
                    "<td>" + data[i].market_cap_usd + "</td>" +
                    "<td>$" + data[i].price_usd + "</td>"; //+

        if (data[i].percent_change_24h > 0) {
            result += "<td class = percent-change-pos>%" + data[i].percent_change_24h + "</td>";
        } else {
            result += "<td class = percent-change-neg>%" + data[i].percent_change_24h + "</td>";
        }

        result += "</tr>";

    }


    result += "</table>";

    return result;
}
