
<script language="javascript">
    var serviceId,gridRequestedActions,calYear;

    $(document).ready(function () {
        calYear = moment().format('YYYY');
        serviceId = ${serviceId}
        onLoadInfoPage();
        initActionsGrid();

        if(!${isMultidept}){
            dropDownService.value(${serviceId});
            dropDownService.readonly(true);
            initialLoadGrid();
        }

    });
    function onLoadInfoPage() {

        $('#year').kendoDatePicker({
            format: "yyyy",
            start: "decade",
            depth: "decade",
            change:initialLoadGrid
        }).data("kendoDatePicker");
        $('#year').val(calYear);
                defaultPageTile("SAP", 'pmActions/showRequestedAction');
    }
    function initialLoadGrid() {
        calYear=$('#year').val();
        serviceId=dropDownService.value();
        var url = "${createLink(controller: 'pmActions', action: 'listRequestedActions')}?year=" + calYear+"&serviceId="+serviceId;
        populateGridKendo(gridRequestedActions, url);

    }
    //////////////////////////// START GRID INIT ////////////////////////////////////////////////////////
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
                        id: {type: "number"},
                        version: {type: "number"},
                        actions: {type: "string"},
                        goalId: {type: "number"},
                        totalIndicator: {type: "number"},
                        resPersonId: {type: "number"},
                        goal: {type: "string"},
                        service: {type: "string"},
                        serShortName: {type: "string"},
                        serviceId: {type: "number"},
                        tmpSeq: {type: "number"},
                        sequence: {type: "string"},
                        resPerson: {type: "string"},
                        strategyMapRef: {type: "string"},
                        supportDepartment: {type: "string"},
                        supportDepartmentStr: {type: "string"},
                        sourceOfFund: {type: "string"},
                        sourceOfFundStr: {type: "string"},
                        note: {type: "string"},
                        indicator: {type: "string"},
                        start: {type: "date"},
                        end: {type: "date"},
                        extendedEnd: {type: "string"}
                    }
                },
                parse: function (data) {
                    checkIsErrorGridKendo(data);
                    return data;
                }
            },
            sort: [
                {field: 'serviceId', dir: 'asc'},
                {field: 'start', dir: 'asc', format: "{0:yyyy}"},
                {field: 'goalId', dir: 'asc'},
                {field: 'tmpSeq', dir: 'asc'}
            ],
            pageSize: getDefaultPageSize(),
            serverPaging: true,
            serverFiltering: true,
            serverSorting: true
        });
    }

    function initActionsGrid() {
        initDataSource();
        $("#gridRequestedActions").kendoGrid({
            dataSource: dataSource,
            autoBind: false,
            height: getGridHeightKendo(),
            selectable: true,
            sortable: true,
            resizable: true,
            reorderable: true,
            detailInit: initIndicator,
            dataBound: function () {
                this.expandRow(this.tbody.find("tr.k-master-row"));
            },
            pageable: {
                refresh: true,
                pageSizes: getDefaultPageSizes(),
                buttonCount: 4
            },
            columns: [
                {
                    field: "serShortName",
                    title: "CSU/Sector",
                    width: 80,
                    sortable: false,
                    filterable: kendoCommonFilterable(98)
                },
                {
                    field: "sequence", title: "ID#", width: 40, sortable: false, filterable: false,
                    attributes: {style: setAlignCenter()}, headerAttributes: {style: setAlignCenter()}
                },
                {field: "actions", title: "Action", width: 200, sortable: false, filterable: false},
                {
                    field: "start", title: "Start Date", width: 60, sortable: false, filterable: false,
                    template: "#=kendo.toString(kendo.parseDate(start, 'yyyy-MM-dd'), 'MMM-yy')#"
                },
                {
                    field: "end", title: "End Date", width: 60, sortable: false, filterable: false,
                    template: "#=formatExtendedDateStrike(kendo.toString(kendo.parseDate(end, 'yyyy-MM-dd'), 'MMM-yy'),extendedEnd)#"
                },
                {field: "resPerson", title: "Responsible Person", width: 90, sortable: false, filterable: false},
                {
                    field: "supportDepartmentStr", title: "Support Department", width: 90,
                    sortable: false, filterable: false
                },
                {field: "sourceOfFundStr", title: "Project", width: 80, sortable: false, filterable: false},
                {
                    field: "note",
                    title: "Remarks",
                    template: "#=trimTextForKendo(note,70)#",
                    width: 120,
                    sortable: false,
                    filterable: false
                }
            ],
            filterable: {
                mode: "row"
            }
        });
        gridRequestedActions = $("#gridRequestedActions").data("kendoGrid");
        $("#menuGrid").kendoMenu();
    }
    $("#gridRequestedActions").kendoTooltip({
        filter: "td:nth-child(9)",
        width: 300,
        position: "top",
        content: function (e) {
            var dataItem = $("#gridRequestedActions").data("kendoGrid").dataItem(e.target.closest("tr"));
            return dataItem.note;
        }
    }).data("kendoTooltip");

    function initIndicator(e) {
        $("<div/>").appendTo(e.detailCell).kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "${createLink(controller: 'pmActions', action: 'listIndicatorByActions')}?",
                        dataType: "json",
                        type: "post"
                    }
                },
                schema: {
                    type: 'json',
                    data: "list"
                },
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                pageSize: 50,
                filter: {field: "actionsId", operator: "eq", value: e.data.id}
            },
            scrollable: false,
            sortable: false,
            pageable: false,
            detailInit: initDetails,
            columns: [
                {field: "indicator", title: "Indicator", width: '55%'},
                {field: "target", title: "Target", template: "#=formatIndicator(indicatorType,target)#", width: '15%'},
                {field: "unitStr", title: "Unit", width: '15%'},
                {field: "indicatorType", title: "Indicator Type", width: '15%'}
            ]
        });
    }
    function formatIndicator(indicatorType, target) {
        if (!target && (indicatorType.match('%'))) {
            return '0 %'
        } else if (!target && (indicatorType.match('%'))) {
            return '0'
        }
        if (indicatorType.match('%')) {
            return target + ' % ';
        }
        return target
    }

    function initDetails(e) {
        var row = e.masterRow[0];
        var indicator = $(row).closest('tr').find('td').eq(1).text();
        var indicatorType = e.data.indicatorType;
        var unitStr = e.data.unitStr;
        var target = e.data.target;
        $("<div/>").appendTo(e.detailCell).kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "${createLink(controller: 'pmActions', action: 'listDetailsByIndicator')}?actionsId=" + e.data.actionsId,
                        dataType: "json",
                        type: "post"
                    }
                },
                schema: {
                    type: 'json',
                    data: "list"
                },
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                pageSize: 10,
                filter: {field: "indicator_id", operator: "eq", value: e.data.id}
            },
            scrollable: false,
            sortable: false,
            pageable: false,
            columns: [
                {field: "month_name", title: "Month",width: '10%'},
                {field: "target", title: "Monthly Target",template:"#=formatIndicator(indicator_type,target)#",width: '15%'},
                {field: "achievement", title: "Monthly Achievement",template:"#=formatIndicatorAcv(month_name,indicator_type,achievement)#",width: '15%'},
                {field: "remarks", title: "Monthly Indicator Remarks",width: '60%', encoded: false}
            ]
        });
    }
    function formatIndicatorAcv(month, indicatorType, target) {
        var monthNo = moment().month(month).format("M");
        if (monthNo > moment().month()) {
            return ''
        }
        if (!target && (indicatorType.match('%'))) {
            return '0 %'
        } else if (!target && (indicatorType.match('%'))) {
            return '0'
        }
        if (indicatorType.match('%')) {
            return target + ' % ';
        }
        return target
    }
</script>
