var pageIndex;//current page，default is 0
var pageSize;//records per page
var totalcount;

jQuery(document).ready(function($) {

    $("#test").submit(function(event) {

        // Disble the search button
        enableSearchButton(false);

        // Prevent the form from submitting via the browser.
        event.preventDefault();

        pageIndex = $("#index").val();
        if (pageIndex == "") {
            pageIndex = 0;
        }
        pageSize = $("#size").val();
        if (pageSize == "") {
            pageSize = 10;
        }

        searchViaAjax();

    });

});

function searchViaAjax() {

    var search = {}
    search["input"] = $("#value").val();
    search["pageIndex"] = pageIndex;//pageIndex;
    search["pageSize"] = pageSize;//pageSize;

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "test",
        data : JSON.stringify(search),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            if (data.length == 0) {
                alert("Empty Input");
            } else {
                totalcount = Math.floor(data[data.length - 1] / pageSize);
            }
            display(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE");
            enableSearchButton(true);
        }
    });
}

function enableSearchButton(flag) {
    $("#btn").prop("disabled", flag);
}

function display(data) {
    var json = "<h4>Combination Output</h4>";

    var start = pageIndex * pageSize;

    $('#feedback').html(json);
    for( var i = 0 ; i < data.length - 1 ; i++){
        $('#feedback').append(start + i + " : " + data[i] + "<br>");
    }
    var page  =  '<a href="javascript:void" onclick="GoToFirstPage()" id="aFirstPage" >First Page</a>      ' +
        '<a href="javascript:void" onclick="GoToPrePage()" id="aPrePage"  >Previous</a> Current Page : ' + pageIndex + ' <a href="javascript:void" onclick="GoToNextPage()" id="aNextPage" >Next</a>      ' +
        '<a href="javascript:void" onclick="GoToEndPage()" id="aEndPage" >Last Page</a>';
    $('#feedback').append(page);

}

//First Page
function GoToFirstPage() {
    pageIndex = 0;
    searchViaAjax( pageIndex, pageSize);
}
//Previous Page
function GoToPrePage() {
    pageIndex -= 1;
    pageIndex = pageIndex >= 0 ? pageIndex : 0;
    searchViaAjax( pageIndex, pageSize);
}
//Next Page
function GoToNextPage() {
    if (pageIndex + 1 <= totalcount) {
        pageIndex += 1;
    }
    searchViaAjax( pageIndex, pageSize);
}
//Last Page
function GoToEndPage() {
    pageIndex = totalcount;// - 1;
    searchViaAjax(pageIndex, pageSize);
}