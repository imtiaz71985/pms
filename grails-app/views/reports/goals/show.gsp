<div class="container-fluid">
    <div class="row">
        <div id="application_top_panel" class="panel panel-primary">
            <div class="panel-heading" style="height: 30px;">
                <div class="panel-title">
                    CSU/Sector wise Goals
                </div>
            </div>

            <g:form name='goalsForm' id='goalsForm' class="form-horizontal form-widgets" role="form">
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-1 control-label label-optional" for="year">Year:</label>

                        <div class="col-md-2">
                            <input type='text' tabindex="1" required="required" onkeydown="return false;"
                                   class="kendo-date-picker" id="year" name="year"
                                   placeholder="Year" validationMessage="Required"/>
                        </div>
                        <label class="col-md-1 control-label label-optional"
                               for="serviceId">Sector/CSU:</label>

                        <div class="col-md-3">
                            <app:dropDownService
                                    class="kendo-drop-down" is_in_sp="true"
                                    id="serviceId" name="serviceId" tabindex="2"
                                    data_model_name="dropDownService">
                            </app:dropDownService>
                        </div>
                        <div class="col-md-2">
                            <button id="create" name="create" type="submit" data-role="button"
                                    class="k-button k-button-icontext"
                                    role="button" tabindex="3"
                                    aria-disabled="false"><span class="k-icon k-i-search"></span>View Result
                            </button>
                        </div>

                    </div>
                </div>
            </g:form>
        </div>
    </div>

    <div class="row">
        <div id="gridGoals"></div>
    </div>
</div>
<g:render template='/reports/goals/scripts'/>




