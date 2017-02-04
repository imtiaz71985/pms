

<script language="javascript">
    var serviceId;

    $(document).ready(function () {
        onLoadEdDashboardPage();
        loadTableData();
    });

    function onLoadEdDashboardPage() {
        serviceId = ${serviceId};

        var str = moment().format('MMMM YYYY');
        var months = $('#month').kendoDatePicker({
            format: "MMMM yyyy",
            parseFormats: ["yyyy-MM-dd"],
            start: "year",
            depth: "year"
        }).data("kendoDatePicker");
        $('#month').val(str);

        initializeForm($("#edDashboardForm"), onSubmitEdDashboard);
        defaultPageTile("Create Ed Dashboard",null);
        dropDownService.value(serviceId);
    }
    function loadTableData(){
        var actionUrl = "${createLink(controller:'edDashboard', action: 'list')}";
        serviceId=$('#serviceId').val();
        var month=$('#month').val();
        jQuery.ajax({
            type: 'post',
            data: {serviceId:serviceId,month:month},
            url: actionUrl,
            success: function (data, textStatus) {
                $('#tableData').html('');
                $('#tableData').html(data.tableHtml);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.info('error');
            },
            complete: function (XMLHttpRequest, textStatus) {
                console.info('complete');
            }

        });
    }

    function executePreCondition() {
        if (!validateForm($("#edDashboardForm"))) {
            return false;
        }
        return true;
    }

    function onSubmitEdDashboard() {
        if (executePreCondition() == false) {
            return false;
        }
        showLoadingSpinner(true);

        jQuery.ajax({
            type: 'post',
            data: jQuery("#edDashboardForm").serialize(),
            url: "${createLink(controller:'edDashboard', action: 'create')}",
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
                emptyForm();
                showSuccess(result.message);
            } catch (e) {
                // Do Nothing
            }
        }
    }

    function emptyForm() {
        clearForm($("#edDashboardForm"), $('#serviceId'));
        dropDownService.value(serviceId);
       // $('#create').html("<span class='k-icon k-i-plus'></span>Create");
    }
    function resetForm() {
        dropDownService.value(serviceId);
    }


</script>
