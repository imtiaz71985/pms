<style>
#page-wrapper {
    background-color: #fff;
}
#dd {
    position: relative;
    font-family: "DejaVu Sans", "Arial", sans-serif;
}

.pdf-header {
    position: absolute;
    top: .2in;
    left: .5in;
    right: .5in;
    border-bottom: 1px solid #e5e5e5;
}

.pdf-body {
    position: absolute;
    top: 1.2in;
    bottom: 1.2in;
    left: .5in;
    right: .5in;
}
</style>
<script language="javascript">
    $(document).ready(function () {
        defaultPageTile("Meeting Log details","meetingLog/show");
    });
</script>
<div id="dd">
    <div class="pdf-header">
        <span class="company-logo">
            <img src="images/logo.png"/>
        </span>
    </div>
    <div class="pdf-body">
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td width="20%" class="active">CSU/Sector</td>
            <td>${resultSet.service}</td>
        </tr>
        <tr>
            <td width="20%" class="active">Date</td>
            <td>${resultSet.held_on}</td>
        </tr>
        <tr>
            <td width="20%" class="active">Attendees</td>
            <td>${resultSet.attendees_str}</td>
        </tr>
        <tr>
            <td width="20%" class="active">Issues</td>
            <td>${resultSet.issues}</td>
        </tr>
        <tr>
            <td width="20%" class="active">Action Log</td>
            <td>${resultSet.log_str}</td>
        </tr>
        </tr>
        </tbody>
    </table>
    </div>

</div>





