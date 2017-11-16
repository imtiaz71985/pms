<script language="javascript">
    var gridGoals,dataSource,serviceId,dropDownService;
    $(document).ready(function() {
        onLoadGoalsPage();
        initGoalGrid();
    });

    function onLoadGoalsPage() {
        var str = moment().format('YYYY');
        $('#year').kendoDatePicker({
            format: "yyyy",
            parseFormats: ["yyyy-MM-dd"],
            start: "decade",
            depth: "decade"
        }).data("kendoDatePicker");
        $('#year').val(str);
        serviceId = ${serviceId};

        initializeForm($("#goalsForm"), onSubmitForm);
        defaultPageTile("CSU/Sector wise Goals","reports/showGoals");
    }

    function onSubmitForm(){
        var year = $('#year').val();
        if(year==''){
            showError('Please select year');
            return false;
        }
        serviceId = dropDownService.value();
        var params = "?year="+year+ "&serviceId=" + serviceId+ "&reportsView=";
        var url ="${createLink(controller: 'reports', action: 'listGoals')}" + params;
        populateGridKendo(gridGoals, url);
        return false;
    }

    function initDataSource() {
        dataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: false,
                    dataType: "json",
                    type: "post"
                }
            },
            schema: {
                type: 'json',
                data: "list", total: "count",
                model: {
                    fields: {
                        id          : {type: "number"},
                        version     : {type: "number"},
                        goal        : {type: "string"},
                        year        : {type: "number"},
                        serviceId   : {type: "number"},
                        service     : {type: "string"},
                        serShortName: {type: "string"},
                        sequence    : {type: "number"}
                    }
                },
                parse: function (data) {
                    checkIsErrorGridKendo(data);
                    return data;
                }
            },
            sort: {field: 'id', dir: 'asc'},
            pageSize: getDefaultPageSize(),
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true
        });
    }

    function initGoalGrid() {
        initDataSource();
        $("#gridGoals").kendoGrid({
            dataSource: dataSource,
            autoBind: false,
            height: getGridHeightKendo(),
            selectable: true,
            sortable: true,
            resizable: true,
            reorderable: true,
            pageable: {
                refresh: true,
                pageSizes: getDefaultPageSizes(),
                buttonCount: 4
            },
            columns: [
                {field: "serShortName", title: "Sector/CSU", width: 30, sortable: false, filterable: false},
                {
                    field: "sequence", title: "ID#", width: 20, sortable: false, filterable: false,
                    attributes: {style: setAlignCenter()}, headerAttributes: {style: setAlignCenter()}
                },
                {field: "goal", title: "Goal Statement", width: 200, sortable: false, filterable: false},
                {
                    field: "year", title: "Year", width: 30, sortable: false, filterable: false,
                    attributes: {style: setAlignCenter()}, headerAttributes: {style: setAlignCenter()}
                }
            ],
            filterable: {
                mode: "row"
            }
        });
        gridGoals = $("#gridGoals").data("kendoGrid");
    }
</script>
