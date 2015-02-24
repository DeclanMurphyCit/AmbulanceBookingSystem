package com.abs.controller;

/**
 * Created by Declan on 09/02/2015.
 */

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import javax.servlet.ServletContext;

        import com.abs.domain.AmbulanceBooking;
        import com.abs.domain.Location;
        import com.abs.domain.Patient;
        import com.abs.service.AmbulanceBookingDAO;
        import com.abs.service.LocationDAO;
        import com.abs.service.PatientDAO;
        import com.google.gson.Gson;
        import org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ambbooking")
public class BookingController {

    @Autowired
    AmbulanceBookingDAO ambulanceBookingDAO;
    @Autowired
    PatientDAO patientDAO;
    @Autowired
    LocationDAO locationDAO;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value={"/displayBookings"}, method = RequestMethod.GET)
    public String listAll(ModelMap model) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);

        Map<Integer,String> listPatients = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(),patientName );
        }

        model.addAttribute("patients", listPatients);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);
        return "displayBookings";
    }

    @RequestMapping(value="/displayBooking", method=RequestMethod.GET)
    public String displayBooking(ModelMap model){
        return "displayBooking";
    }

    @RequestMapping(value="/displayBooking/id/{id}", method=RequestMethod.GET)
    public String displayBookingByBookingNumber(@PathVariable Integer id,
                                                ModelMap model){
        AmbulanceBooking booking=ambulanceBookingDAO.getBooking(id);
        model.addAttribute("ambulancebooking", booking);
        return "displayBooking";
    }

    @RequestMapping(value = "/addNewBooking", method = RequestMethod.GET)
    public String addNewBooking(ModelMap model) {
        List<Patient> patientList = patientDAO.getAllPatientsFromWard(2);
        model.addAttribute("patientList", patientList);
        List<Location> locationList = locationDAO.getAllLocations();
        model.addAttribute("locationList", locationList);
        model.addAttribute("ambulancebooking", new AmbulanceBooking());
        return "addNewBooking";
    }

    @RequestMapping(value = "/addNewBooking", method = RequestMethod.POST)
    public String addBooking(@ModelAttribute("ambulancebooking")  AmbulanceBooking ambulancebooking,
                                      BindingResult result, ModelMap model) {

        if(result.hasErrors())
        {
            System.out.println("ERROR1");
            return "error";
        }

        Integer id;

        try {
            id= ambulanceBookingDAO.createAmbulanceBookingGetId(ambulancebooking.getPatientId(), ambulancebooking.getCreatedBy(),
                    ambulancebooking.getDestination(), ambulancebooking.getOrigin(), ambulancebooking.isCardiac(), ambulancebooking.isUrgent(),
                    ambulancebooking.getDateOfTransfer());

            model.addAttribute("id", id);

        } catch (Exception e) {
            System.out.println("ERROR2");
            model.addAttribute("message", "Creation of booking failed, "+e.getLocalizedMessage());
            return "error";

        }
        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);

        Map<Integer,String> listPatients = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(),patientName );
        }

        model.addAttribute("patients", listPatients);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);

        return "displayBookings";
    }

    @RequestMapping(value={"/bookingPermission"}, method = RequestMethod.GET)
    public String bookingPermission(ModelMap model) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllUnapprovedBookings();
        model.addAttribute("bookings", listBookings);

        String bookingIdArray = " ";

        Map<Integer,String> listPatients = new HashMap<Integer,String>();

        for(AmbulanceBooking b : listBookings)
        {
            bookingIdArray += b.getBookingId() + ", ";
            Patient p = patientDAO.getPatient(b.getPatientId());
            String patientName = p.getFirstName() + " " + p.getLastName();
            listPatients.put(b.getBookingId(),patientName );
        }

        bookingIdArray = bookingIdArray.substring(0, bookingIdArray.length()-1);
        model.addAttribute("bookingIdArray",bookingIdArray);
        model.addAttribute("patients", listPatients);
        List<Location> listLoc = locationDAO.getAllLocations();
        model.addAttribute("locations", listLoc);
        model.addAttribute("numberOfBookings", listBookings.size());

        return "bookingPermission";
    }

    @RequestMapping(value={"/acceptBooking"}, method = RequestMethod.POST   )
    public @ResponseBody String acceptBooking(@ModelAttribute("bookingId") String bookingId, BindingResult result) {
        Integer bid = Integer.parseInt(bookingId);
        ambulanceBookingDAO.setApproval(bid,true,1);
        return "success";
    }

    @RequestMapping(value={"/denyBooking"}, method = RequestMethod.POST   )
    public @ResponseBody String denyBooking(@ModelAttribute("bookingId") String bookingId, BindingResult result) {
        Integer bid = Integer.parseInt(bookingId);
        ambulanceBookingDAO.setApproval(bid,false,1);
        return "success";
    }

    @RequestMapping(value={"/getNewUnapprovedBookings"}, method = RequestMethod.GET   )
    public @ResponseBody String getNewUnapprovedBookings(@ModelAttribute("bookingId") String bookingId, BindingResult result) {

        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllUnapprovedBookings();
        if(listBookings.size() > 0)
        {
            Gson gson = new Gson();
            String json = gson.toJson(listBookings);
            return json;
        }
            return "none";
    }

    @RequestMapping(value="/modifyBooking", method = RequestMethod.GET)
    public String modify(ModelMap model) {
        List<AmbulanceBooking> bookings=ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "modifyBooking";
    }

    @RequestMapping(value ="/modifyBookingForm/id/{id}", method = RequestMethod.GET)
    public String modifyBookingByID(@PathVariable Integer id, ModelMap model) {
        AmbulanceBooking bookingModify=ambulanceBookingDAO.getBooking(id);
        model.addAttribute("message", "Booking with id "+ id +" can now be modified");
        model.addAttribute("booking", bookingModify);
        return "modifyBookingForm";
    }

/*   @RequestMapping(value ="/modifyBookingForm/id/{id}/destination/{destination}"
            + "/origin/{origin}/cardiac/{cardiac}/urgent/{urgent}"
            + "/approved/{approved}/transferDateTime/{transferDateTime}", method = RequestMethod.GET)
    public String modifyBookingByID(@PathVariable Integer id,@PathVariable Integer destination,@PathVariable Integer origin,
                                    @PathVariable boolean cardiac,@PathVariable boolean urgent,@PathVariable boolean approved,
           @PathVariable String transferDateTime, ModelMap model) {

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


    @RequestMapping(value ="/removeBooking/id/{id}", method = RequestMethod.GET)
    public String deleteBookingById(@PathVariable Integer id, ModelMap model) {
        AmbulanceBooking bookingDelete = ambulanceBookingDAO.getBooking(id);
        ambulanceBookingDAO.deleteAmbulanceBooking(id);
        model.addAttribute("message", "Booking  has been deleted from the system");
        List<AmbulanceBooking> listBookings = ambulanceBookingDAO.getAllBookings();
        model.addAttribute("bookings", listBookings);
        return "removeBooking";
    }
}