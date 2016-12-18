<script type="text/x-kendo-template" id="gridToolbar">
<ul id="menuGrid" class="kendoGridMenu">
    <sec:access url="/pmActions/create">
        <li onclick="addService();"><i class="fa fa-plus-square"></i>Add</li>
    </sec:access>
    <sec:access url="/pmActions/update">
        <li onclick="editService();"><i class="fa fa-edit"></i>Edit</li>
    </sec:access>
    <sec:access url="/pmActions/delete">
        <li onclick="deleteService();"><i class="fa fa-trash-o"></i>Delete</li>
    </sec:access>
</ul>
</script>

<script language="javascript">
    var gridActions, dataSource, actionsModel, dropDownService, serviceId, dropDownGoals, supportDepartment, sourceOfFund, dropDownEmployee, st;
    var map = {};
    var IndCount = 1;


    $(document).ready(function () {
        onLoadActionPage();
        initActionsGrid();
        initObservable();
        $(document).on("keyup", ".amount", calculateSum);
    });
    function validateQty(event) {
        var key = window.event ? event.keyCode : event.which;
        if (event.keyCode == 8 || event.keyCode == 46
                || event.keyCode == 37 || event.keyCode == 39) {
            return true;
        }
        else if (key < 48 || key > 57) {
            return false;
        }
        else return true;
    }
    function getName(name, target) {
            var index = name.slice(-1);
            var indName = 'indicator' + index;
            var tarName = 'target' + index;
            var tmpInd = 'input[name=' + indName + ']';
            var tmpTar = 'input[name=' + tarName + ']';
            var indicator = $(tmpInd).val();
            var start = $('#start').val();
            var end = $('#end').val();
            var count = monthDifference(start, end);
            var list = monthNamesFromRange(start, end);
            if (count > 1 && target > 0 && indicator != '') showIndicatorModal(indName, indicator, tmpTar, target, count, list);
    }

    function add_row(tmpId){
        var indic = "#indicator" + tmpId;
        var targ = "#target" + tmpId;
        var indicator = $(indic).val();
        var target = $(targ).val();
        if(indicator == '' || indicator == undefined || target == ''|| target == undefined){
            showError('Please insert current indicator & target');
            return false;
        }
        var trIdNo = IndCount + 1;
        var trId = 'addr' + trIdNo;
        var trData = "<tr id='" + trId + "'><td width='70%'>" +
                "<input id='indicator" + trIdNo + "' name='indicator" + trIdNo + "' type='text'  placeholder='Indicator' class='form-control'/>" +
                "</td>" +
                "<td width='20%'>" +
                "<input  id='target" + trIdNo + "' name='target" + trIdNo + "' type='text' onkeypress='return validateQty(event);' placeholder='Target' class='form-control'  onblur ='getName(this.name,this.value)'>" +
                "</td>" +
                "<td>" +
                "<a class='addbtn' onclick='add_row("+trIdNo+")'><i class='fa fa-plus'></i></a>" +
                "</td>" +
                "<td>" +
                "<a class='delbtn' onclick='del_row("+trIdNo+")'><i class='fa fa-remove'></i></a>" +
                "</td>" +
                "</tr>";
        $('#tab_logic').append(trData);
        IndCount++;
    }
    function del_row(trIdNo){
        var trCount = $('#tab_logic tr').length;
        if (trCount > 1) {
            var trId = '#addr' + trIdNo;
            $(trId).remove();
        }
    }
    function onLoadActionPage() {
        $("#rowAction").hide();

        var start = $('#start').kendoDatePicker({
            format: "MMMM yyyy",
            parseFormats: ["yyyy-MM-dd"],
            start: "year",
            depth: "year",
            change: startChange
        }).data("kendoDatePicker");

        var end = $('#end').kendoDatePicker({
            format: "MMMM yyyy",
            parseFormats: ["yyyy-MM-dd"],
            start: "year",
            depth: "year",
            change: endChange
        }).data("kendoDatePicker");

        function startChange() {
            var value = start.value();
            if (value == '' || value == null) {
                $('#end').val('');
            }
            if (value) {
                st = new Date(moment(value).endOf('year'));
                end.min(value);
                end.max(st);
            }
        }
        function endChange() {
            var value = end.value();
            var start = $('#start').val();
            if (start == '') {
                $('#end').val('');
                $('#start').focus();
                return false;
            }
            if (value != '' || value != null) {
                $(':input').prop('readonly', false);
            }
        }

        dropDownGoals = initKendoDropdown($('#goalId'), null, null, null);

        $("#supportDepartment").kendoMultiSelect({
            dataTextField: "name",
            dataValueField: "id",
            filter: "contains",
            suggest: true,
            dataSource: getBlankDataSource
        });
        supportDepartment = $("#supportDepartment").data("kendoMultiSelect");
        supportDepartment.setDataSource(${lstService});

        $("#sourceOfFund").kendoMultiSelect({
            dataTextField: "name",
            dataValueField: "id",
            filter: "contains",
            suggest: true,
            dataSource: getBlankDataSource
        });
        sourceOfFund = $("#sourceOfFund").data("kendoMultiSelect");
        sourceOfFund.setDataSource(${lstProject});
        serviceId = ${serviceId};
        initializeForm($("#actionForm"), onSubmitAction);
        defaultPageTile("Create Actions", null);
    }

    function executePreCondition() {
        if ($("#serviceId").val() == '') {
            showError('Please select any service.');
            return false;
        } else if ($("#goalId").val() == '') {
            showError('Please select any goal.');
            return false;
        } else if ($("#start").val() == '') {
            showError('Please select start date.');
            return false;
        } else if ($("#end").val() == '') {
            showError('Please select end date.');
            return false;
        } else if ($("#indicator1").val() == '') {
            showError('Please insert indicator.');
            return false;
        } else if ($("#target1").val() == '') {
            showError('Please insert target.');
            return false;
        } else if ($("#actions").val() == '') {
            showError('Please insert actions.');
            return false;
        } else if ($("#resPersonId").val() == '') {
            showError('Please select responsible person.');
            return false;
        }
        return true;
    }

    function onSubmitAction() {
        if (executePreCondition() == false) {
            return false;
        }
        var count = $('#tab_logic tr').length;
        $("#indicatorCount").val(count);
        $("#indicatorMaxId").val(IndCount);


/*        setButtonDisabled($('#create'), true);
        showLoadingSpinner(true);*/
        var actionUrl = null;
        if ($('#id').val().isEmpty()) {
            actionUrl = "${createLink(controller:'pmActions', action: 'create')}";
        } else {
            actionUrl = "${createLink(controller:'pmActions', action: 'update')}";
        }
        var resPerson = $("#resPersonId").data("kendoDropDownList").text();
        jQuery.ajax({
            type: 'post',
            data: jQuery("#actionForm").serialize() + '&resPerson=' + resPerson,
            url: actionUrl,
            success: function (data, textStatus) {
                executePostCondition(data);
                setButtonDisabled($('#create'), false);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
            complete: function (XMLHttpRequest, textStatus) {
                showLoadingSpinner(false);
            },
            dataType: 'json'
        });
        return false;
    }

    function executePostCondition(result) {
        if (result.isError) {
            showError(result.message);
            showLoadingSpinner(false);
        } else {
            try {
                var newEntry = result.pmAction;
                if ($('#id').val().isEmpty() && newEntry != null) { // newly created
                    var gridData = gridActions.dataSource.data();
                    gridData.unshift(newEntry);
                } else if (newEntry != null) { // updated existing
                    var selectedRow = gridActions.select();
                    var allItems = gridActions.items();
                    var selectedIndex = allItems.index(selectedRow);
                    gridActions.removeRow(selectedRow);
                    gridActions.dataSource.insert(selectedIndex, newEntry);
                }
                emptyForm();
                showSuccess(result.message);
            } catch (e) {
                // Do Nothing
            }
        }
    }

    function emptyForm() {
        clearForm($("#actionForm"), null);
        initObservable();
        dropDownService.value(serviceId);
        dropDownGoals.value('');
        map = {};
        clearIndicatorTable();
    }
    function resetForm() {
        clearForm($("#actionForm"), null);
        initObservable();
        dropDownService.value(serviceId);
        dropDownGoals.value('');
        clearIndicatorTable();
        $("#rowAction").hide();
        $('#create').html("<span class='k-icon k-i-plus'></span>Create");
    }

    function initDataSource() {
        dataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: "${createLink(controller: 'pmActions', action: 'list')}",
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
                        sequence: {type: "string"},
                        resPerson: {type: "string"},
                        strategyMapRef: {type: "string"},
                        supportDepartment: {type: "string"},
                        supportDepartmentStr: {type: "string"},
                        sourceOfFund: {type: "string"},
                        sourceOfFundStr: {type: "string"},
                        note: {type: "string"},
                        start: {type: "date"},
                        end: {type: "date"}
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

    function initActionsGrid() {
        initDataSource();
        $("#gridActions").kendoGrid({
            dataSource: dataSource,
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
                    field: "sequence", title: "ID#", width: 40, sortable: false, filterable: false,
                    attributes: {style: setAlignCenter()}, headerAttributes: {style: setAlignCenter()}
                },
                {field: "actions", title: "Action", width: 200, sortable: false, filterable: false},
                {
                    field: "start", title: "Start Date", width: 60, sortable: false, filterable: false,
                    template: "#=kendo.toString(kendo.parseDate(start, 'yyyy-MM-dd'), 'MMMM-yy')#"
                },
                {
                    field: "end", title: "End Date", width: 60, sortable: false, filterable: false,
                    template: "#=kendo.toString(kendo.parseDate(end, 'yyyy-MM-dd'), 'MMMM-yy')#"
                },
                {field: "resPerson", title: "Responsible Person", width: 120, sortable: false, filterable: false},
                {
                    field: "supportDepartmentStr", title: "Support Department", width: 120,
                    sortable: false, filterable: false
                },
                {field: "sourceOfFundStr", title: "Project", width: 80, sortable: false, filterable: false},
                {field: "note", title: "Remarks", template:"#=trimTextForKendo(note,100)#", width: 80, sortable: false, filterable: false}
            ],
            filterable: {
                mode: "row"
            },
            toolbar: kendo.template($("#gridToolbar").html())
        });
        gridActions = $("#gridActions").data("kendoGrid");
        $("#menuGrid").kendoMenu();
    }
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
                pageSize: 10,
                filter: {field: "actionsId", operator: "eq", value: e.data.id}
            },
            scrollable: false,
            sortable: false,
            pageable: false,
            detailInit: initDetails,
            columns: [
                {field: "indicator", title: "Indicator"},
                {field: "target", title: "Target"}
            ]
        });
    }
    function initDetails(e) {
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
                filter: {field: "indicatorId", operator: "eq", value: e.data.id}
            },
            scrollable: false,
            sortable: false,
            pageable: false,
            columns: [
                {field: "monthName", title: "Month"},
                {field: "target", title: "Monthly Target"}
            ]
        });
    }
    function initObservable() {
        actionsModel = kendo.observable(
                {
                    actions: {
                        id: "",
                        version: "",
                        sequence: "",
                        actions: "",
                        serviceId: "",
                        resPersonId: "",
                        goalId: "",
                        resPerson: "",
                        strategyMapRef: "",
                        supportDepartment: "",
                        sourceOfFund: "",
                        note: "",
                        start: "",
                        end: ""
                    }
                }
        );
        kendo.bind($("#application_top_panel"), actionsModel);
    }

    function deleteService() {
        if (executeCommonPreConditionForSelectKendo(gridActions, 'action') == false) {
            return;
        }
        var msg = 'Are you sure you want to delete the selected action?',
                url = "${createLink(controller: 'pmActions', action:  'delete')}";
        confirmDelete(msg, url, gridActions);
    }

    function addService() {
        $("#rowAction").show();
        dropDownService.value(serviceId);
        populateGoals();
    }
    function editService() {
        if (executeCommonPreConditionForSelectKendo(gridActions, 'action') == false) {
            return;
        }
        addService();
        var actions = getSelectedObjectFromGridKendo(gridActions);
        showService(actions);

        $.ajax({
            url: "${createLink(controller: 'pmActions', action: 'listIndicatorByActions')}?actionsId=" + actions.id,
            success: function (data) {
                clearIndicatorTable();
                $(':input').prop('readonly', false);
                $('input[name=indicator1]').val(data.list[0].indicator);
                $('input[name=target1]').val(data.list[0].target);

                for (var i = 1; i < data.count; i++) {
                    var trCount = $('#tab_logic tr').length;
                    var trIdNo = trCount + 1;
                    var trId = 'addr' + trIdNo;
                    var trData = "<tr id='" + trId + "'><td width='60%'>" +
                            "<input name='indicator" + trIdNo + "' type='text' value='" + data.list[i].indicator + "'  placeholder='Indicator' class='form-control'/>" +
                            "</td>" +
                            "<td width='40%'>" +
                            "<input  name='target" + trIdNo + "' type='text' value='" + data.list[i].target + "' onkeypress='return validateQty(event);' placeholder='Target' class='form-control'  onblur ='getName(this.name,this.value)'>" +
                            "</td></tr>";
                    $('#tab_logic').append(trData);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                afterAjaxError(XMLHttpRequest, textStatus);
            },
            complete: function (XMLHttpRequest, textStatus) {
                showLoadingSpinner(false);
            },
            dataType: 'json',
            type: 'post'
        });
    }

    function clearIndicatorTable(){
        $('input[name=indicator1]').val('');
        $('input[name=target1]').val('');
        for (var i = 1; i <= IndCount; i++) {
                var trId = '#addr' + i;
                $(trId).remove();
        }
        var trData = "<tr id='" + trId + "'><td width='60%'>" +
                "<input id='indicator1' name='indicator1' type='text' placeholder='Indicator' class='form-control'/>" +
                "</td>" +
                "<td width='40%'>" +
                "<input id='target1' name='target1' type='text' onkeypress='return validateQty(event);' placeholder='Target' class='form-control'  onblur ='getName(this.name,this.value)'>" +
                "</td>" +
                "<td>" +
                "<a class='addbtn' onclick='add_row(1)'><i class='fa fa-plus'></i></a>" +
                "</td>" +
                "<td>" +
                "<a class='delbtn' onclick='del_row(1)'><i class='fa fa-remove'></i></a>" +
                "</td>" +
                "</tr>";
        $('#tab_logic').append(trData);
        IndCount = 1;
    }

    function showService(actions) {
        actionsModel.set('actions', actions);
        dropDownEmployee.value(actions.resPersonId);
        if (actions.supportDepartment) supportDepartment.value(actions.supportDepartment.split(","));
        populateGoals(actions.serviceId, actions.goalId);
        $('#create').html("<span class='k-icon k-i-plus'></span>Update");
    }

    // To populate Goals List
    function populateGoals(serId, goalId) {
        var serviceId = serId ? serId : dropDownService.value();
        if (serviceId == '') {
            dropDownGoals.setDataSource(getKendoEmptyDataSource(dropDownGoals, null));
            dropDownGoals.value('');
            return false;
        }
        showLoadingSpinner(true);
        $.ajax({
            url: "${createLink(controller: 'pmGoals', action: 'lstGoalsByServiceId')}?serviceId=" + serviceId,
            success: function (data) {
                if (data.isError) {
                    showError(data.message);
                    return false;
                }
                dropDownGoals.setDataSource(data.lstGoals);
                if (goalId) {
                    dropDownGoals.value(goalId);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                afterAjaxError(XMLHttpRequest, textStatus);
            },
            complete: function (XMLHttpRequest, textStatus) {
                showLoadingSpinner(false);
            },
            dataType: 'json',
            type: 'post'
        });
        return true;
    }

    function showIndicatorModal(indName, indicator, tmpTar, target, count, list) {
        $("#createIndicatorModal").modal('show');
        $('#indicatorIdModal').val(indName);
        $('#indicatorModalIndicatorLbl').text(indicator);
        $('#indicatorModalTargetLbl').text('0/'+target);
        $('#hidTargetModal').val(target);
        $('#tempTargetNameModal').val(tmpTar);
        $('#tempCountModal').val(count);
        $("#i_logic tr").remove();
        for (var i = 0; i < count + 1; i++) {
                $('#iddr' + i).html("<td width='60%'><input name='month" + i + "' value ='" + list[i - 1] + "' type='text' readonly='true' class='form-control'/></td>" +
                "<td width='40%'><input class='form-control amount'  value='' name='tempTr" + i + "' type='text' placeholder='Target' ></td>");
                $('#i_logic').append('<tr id="iddr' + (i + 1) + '"></tr>');
        }
    }
    function calculateSum() {
        var target = $('#hidTargetModal').val();
        var sum = 0;
        $(".amount").each(function () {
            if (!isNaN(this.value) && this.value.length != 0) {
                sum += parseInt(this.value);
            }
        });
        $('#indicatorModalTargetLbl').text(sum+'/'+target);
        $('#hidSumModal').val(sum);
    }

    function hideCreateIndicatorModal() {
        var temp = $('#tempTargetNameModal').val();
        $(temp).val('');
        emptyModal();
    }
    function emptyModal() {
        clearForm("#createIndicatorForm", null);
        $('#indicatorModalIndicatorLbl').text('');
        $('#indicatorModalTargetLbl').text('');
        $("#createIndicatorModal").modal('hide');
    }

    function onClickCreateIndicatorModal() {
        var sum = $('#hidSumModal').val();
        var target = $('#hidTargetModal').val();
        if(sum!=target){
            showError('Target split mis-calculated');
            return false;
        }
        var values = jQuery("#createIndicatorForm").serialize();
        var index = $("#indicatorIdModal").val();
        map[index] = values;
        $("#indicator").val(JSON.stringify(map));
        emptyModal();
    }

</script>
