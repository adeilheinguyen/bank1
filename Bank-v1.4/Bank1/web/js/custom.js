$(document).ready(function () {

    $('.owl-carousel').owlCarousel({
        loop: true,
        autoplay: true,
        autoplayTimeout: 5000,
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 1
            },
            1000: {
                items: 1
            }
        }
    });

//    $('#btnlayma').click(function (){
//        $('#btnlayma1').trigger("click");
//    });
//    
    $('#btnlayma1').click(function (e) {
        // e.preventDefault();
        var a = $('#nameaccount').val();
        $('#nameaccount1').val(a);

        var b = $('#email').val();
        $('#email1').val(b);

    });

    $("input[name='kyhangui']").on('keyup keypress blur change', function (e) {
        if (e.which !== 8 && e.which !== 0 && (e.which < 48 || e.which > 57)) {
            return false;
        } else {
            if ($(this).val().length >= parseInt($(this).attr('maxlength')) && (e.which !== 8 && e.which !== 0)) {
                return false;
            }
        }
    });



});