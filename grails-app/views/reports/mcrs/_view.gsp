<div class="container-fluid">
    <div class="row" id="rowGoals">
        <div id="application_top_panel" class="panel panel-primary">
            <div class="panel-heading" style="height: 30px;">
                <div class="panel-title">
                    Management Change Reporting System (MCRS)
                <button id="downloadSP" name="downloadSP" type="button" data-role="button"
                        class="k-button k-button-icontext pull-right" role="button"
                        aria-disabled="false" onclick='downloadMcrsReport();'><span
                        class="fa fa-file-pdf-o"></span>&nbsp;Download
                </button>
                    <input type="checkbox" name="downloadType" id="downloadType"
                           checked="checked" class="pull-right"/>
                </div>
            </div>

            <g:form name='detailsForm' id='detailsForm' class="form-horizontal form-widgets" role="form">
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-1 control-label label-optional" for="month">Month:</label>

                        <div class="col-md-2">
                            <input type='text' tabindex="1" required="required" onkeydown="return false;"
                                   class="kendo-date-picker" id="month" name="month"
                                   placeholder="Month" validationMessage="Required"/>
                        </div>
                        <label class="col-md-1 control-label label-optional"
                               for="serviceId">Service:</label>

                        <div class="col-md-3">
                            <app:dropDownService
                                    class="kendo-drop-down" is_in_sp="true"
                                    id="serviceId" name="serviceId" tabindex="2"
                                    data_model_name="dropDownService">
                            </app:dropDownService>
                        </div>
                        <div class="col-md-2">
                            <select class="kendo-drop-down" id="indicatorType" name="indicatorType" tabindex="3"></select>
                        </div>
                        <div class="col-md-2">
                            <button id="create" name="create" type="submit" data-role="button"
                                    class="k-button k-button-icontext"
                                    role="button" tabindex="4"
                                    aria-disabled="false"><span class="k-icon k-i-search"></span>View Result
                            </button>
                        </div>

                    </div>
                </div>
            </g:form>
        </div>
    </div>
    <div class="row">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">MRP</a></li>
            <li><a data-toggle="tab" href="#menu2">ED's Dashboard</a></li>
        </ul>

        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active">
                <div class="panel-primary">
                    <div id="gridMRP"></div>
                </div>
            </div>
            <div id="menu2" class="tab-pane fade">
                <div class="panel-primary">
                    <div class="form-group" id="tableData">
                        <g:render template='/reports/mcrs/ViewED'/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>