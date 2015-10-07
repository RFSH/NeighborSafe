$(function () {
    $("#add-family").on('click', function () {
        add_family_member();
    });
    //$(".remove-family").on('click', function () {
    //    $(this).closest(".family-member").remove();
    //});
    $("#remove-family").on('click', function(){
        try{
            $(".family-members .fields").last().remove();
        }catch(e){
        }
    });
});

function add_family_member() {
    try{
        var number = parseInt($(".family-members .fields").last().attr('id').split("-")[1]) + 1;
    }catch(e){
        number = 1;
    }
    if(number>6){
        message("Just 6 family members is enough!", "warning");
        return;
    }

    var html = '<div class="fields family-member" id="member-' + number + '">';
    html += '<div class="four wide field"><label>Name</label>';
    html += '<input type="text" name="name_' + number + '" placeholder="Name"/>';
    html += '</div><div class="four wide field"><label>Email</label>';
    html += '<input type="text" name="email_' + number + '" placeholder="Email"/>';
    html += '</div><div class="four wide field"><label>Cell Number</label>';
    html += '<input type="text" name="cell_' + number + '" placeholder="Cell Number"/>';
    html += '</div><div class="two wide field"><label>Age</label>';
    html += '<input type="number" name="age_' + number + '" placeholder="Age"/></div></div>';
    //html += '<div class="three wide field"><label>Remove Member</label>';
    //html += '<div class="ui button red remove-family icon"><i class="icon remove"></i></div></div>';
    $(".family-members").append(html);

    //$(".remove-family").on('click', function () {
    //    $(this).closest(".family-member").remove();
    //});
}