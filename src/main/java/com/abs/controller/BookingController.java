package com.abs.controller;

/**
 * Created by Declan on 09/02/2015.
 */

        import java.util.ArrayList;
        import java.util.List;

        import javax.servlet.ServletContext;
        import javax.validation.Valid;

        import com.abs.domain.AmbulanceBooking;
        import com.abs.service.AmbulanceBookingDAO;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ambbooking")
public class BookingController {

    @Autowired
    AmbulanceBookingDAO ambulanceBookingDAO;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value={"/displayBookings"}, method = RequestMethod.GET)
    public String listAll(ModelMap model) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);
        return "displayBookings";
    }

    @RequestMapping(value="/displayBooking", method=RequestMethod.GET)
    public String getBookingByBookingNumber(ModelMap model){
        return "displayBooking";
    }

    @RequestMapping(value="/displayBooking/id/{id}", method=RequestMethod.GET)
    public String displayBookingByBookingNumber(@PathVariable int id,
                                                ModelMap model){
        AmbulanceBooking booking=ambulanceBookingDAO.getBooking(id);
        model.addAttribute("booking", booking);
        return "displayBooking";
    }

    @RequestMapping(value = "/addNewBooking", method = RequestMethod.GET)
    public String addNewBooking(ModelMap model) {
        model.addAttribute("booking", new AmbulanceBooking());
        return "addNewBooking";
    }


    @RequestMapping(value = "/addNewBooking", method = RequestMethod.POST)
    public String displayAddedBooking(@ModelAttribute("ambulancebooking") @Valid AmbulanceBooking booking,
                                      BindingResult result, ModelMap model) {

        if(result.hasErrors())
            return "addNewBooking";
        int id;
        try {
            id= ambulanceBookingDAO.createAmbulanceBookingGetId(booking.getPatientId(), booking.getCreatedBy(),
                    booking.getDestination(), booking.getOrigin(), booking.isCardiac(), booking.isUrgent(),
                    booking.getDateOfTransfer());

            model.addAttribute("id", id);

        } catch (Exception e) {
            model.addAttribute("message", "Creation of booking failed, "+e.getLocalizedMessage());
            return "errorBooking";

        }
        model.addAttribute(booking);
        return "displayBooking";
    }

    @RequestMapping(value="/modifyBooking", method = RequestMethod.GET)
    public String modify(ModelMap model) {
        List<AmbulanceBooking> bookings=ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "modifyBooking";
    }

    @RequestMapping(value ="/modifyBookingForm/id/{id}", method = RequestMethod.GET)
    public String modifyBookingByID(@PathVariable int id, ModelMap model) {
        AmbulanceBooking bookingModify=ambulanceBookingDAO.getBooking(id);
        model.addAttribute("message", "Booking with id "+ id +" can now be modified");
        model.addAttribute("booking", bookingModify);
        return "modifyBookingForm";
    }

/*    @RequestMapping(value ="/modifyBookingForm/id/{id}/firstName/{firstName}"
            + "/lastName/{lastName}/bookingNumber/{bookingNumber}/email/{email}"
            + "/phoneNumber/{phoneNumber}/addressLine1/{addressLine1}/addressLine2/{addressLine2}", method = RequestMethod.GET)
    public String modifyBookingByID(@PathVariable int id,@PathVariable String firstName,
                                    @PathVariable String lastName,@PathVariable String bookingNumber,@PathVariable String email,
                                    @PathVariable String phoneNumber,@PathVariable String addressLine1,@PathVariable String addressLine2, ModelMap model) {

        ambulanceBookingDAO.updateBooking(id, firstName, lastName, bookingNumber, email, phoneNumber, addressLine1, addressLine2);

        AmbulanceBooking bookingModify=ambulanceBookingDAO.getBooking(id);
        model.addAttribute("message", "Booking " + bookingModify.getFirstName() + " "
                + bookingModify.getLastName() + " has beed successfully updated");

        model.addAttribute("booking", bookingModify);
        return "displayBooking";
    }*/

    @RequestMapping(value = "/removeBooking", method = RequestMethod.GET)
    public String deleteBooking(ModelMap model) {
        List<AmbulanceBooking> listBookings=ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);
        return "removeBooking";
    }
/*
    @RequestMapping(value ="/removeBooking/id/{id}", method = RequestMethod.GET)
    public String deleteBookingById(@PathVariable int id, ModelMap model) {
        AmbulanceBooking bookingDelete=ambulanceBookingDAO.getBooking(id);
        ambulanceBookingDAO.deleteAmbulanceBooking(id);
        model.addAttribute("message", "Booking " + bookingDelete.getFirstName() + " " +
                bookingDelete.getLastName() + " has been deleted from the system");
        model.addAttribute("firstName", bookingDelete.getFirstName());
        model.addAttribute("lastName", bookingDelete.getLastName());
        model.addAttribute("bookingNumber", bookingDelete.getBookingNumber());
        List<AmbulanceBooking> listBookings=ambulanceBookingDAO.listBookings();
        model.addAttribute("bookings", listBookings);
        return "removeBooking";
    }*/

}
