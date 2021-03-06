<div class="container-fluid">
    <div class="row">
        <div class="panel panel-primary">

                    <div id="gridMCRSSubmission"></div>

        </div>
    </div>
</div>


<style type="text/css">
.panel-body {
    padding: 0px;
}
</style>
<script type="text/x-kendo-template" id="gridToolbar">
<ul id="menuGrid2" class="kendoGridMenu">
    <sec:access url="/pmMcrsLog/submission">
        <li onclick="mrpSubmission();"><i class="fa fa-gavel"></i>Submit MRP</li>
    </sec:access>
    <sec:access url="/pmMcrsLog/submissionDashBoard">
        <li onclick="dashboardSubmission();"><i class="fa fa-gavel"></i>Submit ED's Dashboard</li>
    </sec:access>
    <div class="pull-right">
        <table>
            <tr><td>
                <input type="text" id="year" name="year">
            </td></tr>
        </table>
    </div>
</ul>
</script>
<script language="javascript">
    var gridMCRSSubmission, dataSource2, serviceId, kendoDatePicker, currentYear;

    $(document).ready(function () {
        serviceId = ${serviceId};
        currentYear = moment().format('YYYY');
        initMcrsLogGrid();
        $('#year').kendoDatePicker({
            format: "yyyy",
            parseFormats: ["yyyy"],
            depth: "decade",
            start: "decade",
            change: populateGrid
        });
        $('#year').val(currentYear);
        populateGrid();
    });
    function populateGrid() {
        if (currentYear == '') {
            showError("Please select year");
            return false;
        }
        currentYear = $('#year').val();
        var url = "${createLink(controller: 'pmMcrsLog', action: 'list')}?serviceId=" + serviceId + "&year=" + currentYear;
        populateGridKendo(gridMCRSSubmission, url);
    }
    function initDataSource() {
        dataSource2 = new kendo.data.DataSource({
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
                        id: {type: "number"},
                        version: {type: "number"},
                        serviceId: {type: "number"},
                        service: {type: "string"},
                        shortName: {type: "string"},
                        submissionDate: {type: "date"},
                        submissionDateDb: {type: "date"},
                        isSubmitted: {type: "boolean"},
                        isSubmittedDb: {type: "boolean"},
                        isEditable: {type: "boolean"},
                        isEditableDb: {type: "boolean"},
                        year: {type: "number"},
                        monthStr: {type: "string"},
                        month: {type: "number"},
                        issueCount: {type: "number"}
                    }
                },
                parse: function (data) {
                    checkIsErrorGridKendo(data);
                    return data;
                }
            },
            sort: {field: 'month', dir: 'asc'},
            pageSize: getDefaultPageSize(),
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true
        });
    }

    function initMcrsLogGrid() {
        initDataSource();
        $("#gridMCRSSubmission").kendoGrid({
            dataSource: dataSource2,
            height: getGridHeightKendo(),
            selectable: true,
            sortable: true,
            resizable: true,
            reorderable: true,
            autoBind:false,
            pageable: {
                refresh: true,
                pageSizes: getDefaultPageSizes(),
                buttonCount: 4
            },
            columns: [
                {
                    field: "monthStr", title: "Month", width: 30, sortable: false, filterable: false
                },
                {
                    title: "Submission Date", headerAttributes: {style: setAlignCenter()}, filterable: false,
                    columns: [
                        {
                            field: "submissionDate", title: "MRP",
                            width: 30, sortable: false, filterable: false,
                            headerAttributes: {style: setAlignCenter()},
                            attributes: {style: setAlignCenter()},
                            template: "#=isSubmitted?submissionDate==null?'':kendo.toString(kendo.parseDate(submissionDate, 'yyyy-MM-dd'), 'dd-MMMM-yyyy'):''#"
                        },
                        {
                            field: "submissionDateDb", title: "ED's Dashboard",
                            width: 40, sortable: false, filterable: false,
                            headerAttributes: {style: setAlignCenter()},
                            attributes: {style: setAlignCenter()},
                            template: "#=submissionDateDb?kendo.toString(kendo.parseDate(submissionDateDb, 'yyyy-MM-dd'), 'dd-MMMM-yyyy'):''#"
                        }
                    ]
                }
            ],
            filterable: {
                mode: "row"
            },
            toolbar: kendo.template($("#gridToolbar").html())
        });
        gridMCRSSubmission = $("#gridMCRSSubmission").data("kendoGrid");
        $("#menuGrid2").kendoMenu();
    }

    function mrpSubmission() {
        if (executeCommonPreConditionForSelectKendo(gridMCRSSubmission, 'month') == false) {
            return;
        }
        var isSubmitted = getSelectedValueFromGridKendo(gridMCRSSubmission, 'isSubmitted');
        if (isSubmitted) {
            showInfo('MRP already submitted');
            return false;
        }
        var msg = 'Are you sure you want to submit the selected MRP?',
                url = "${createLink(controller: 'pmMcrsLog', action:  'submission')}";
        confirmActionForEdit(msg, url, gridMCRSSubmission);
    }
    function dashboardSubmission() {
        if (executeCommonPreConditionForSelectKendo(gridMCRSSubmission, 'month') == false) {
            return;
        }
        var isSubmitted = getSelectedValueFromGridKendo(gridMCRSSubmission, 'isSubmittedDb');
        if (isSubmitted) {
            showInfo('Dashboard already submitted');
            return false;
        }
        var msg = 'Are you sure you have no issue reporting to ED and want to submit the ED Dashboard?';
        if (getSelectedValueFromGridKendo(gridMCRSSubmission, 'issueCount') > 0)
            msg = 'Are you sure you want to submit the ED Dashboard?';
        var url = "${createLink(controller: 'pmMcrsLog', action:  'submissionDashBoard')}";
        confirmActionForEdit(msg, url, gridMCRSSubmission);
    }

</script>
