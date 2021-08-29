package com.hunzaconsulting.catererservice.startup;

import com.hunzaconsulting.catererservice.domain.Address;
import com.hunzaconsulting.catererservice.domain.Capacity;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.domain.Contact;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CatererBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CatererRepository catererRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        catererRepository.deleteAll();
        loadCaterers();
    }

    private void loadCaterers() {

        saveCaterer("Adnaan Metcalfe", "Lahore", "21 Edgefield Ave.", 1, 10, "mschwartz@live.com", "(641)243-3564");
        saveCaterer("Mathilda Hahn", "Lahore", "Delray Beach, FL 33445", 1, 10, "alhajj@yahoo.ca", "(216)271-5316");
        saveCaterer("Cassidy Pitts", "Lahore", "9533 Joy Ridge Lane", 1, 10, "webteam@aol.com", "(276)356-9507");
        saveCaterer("Zunairah Good", "Lahore", "Beaver Falls, PA 15010", 1, 10, "torgox@msn.com", "(323)344-6569");
        saveCaterer("Eliana Liu", "Lahore", "746 N. Roosevelt Avenue", 1, 10, "stefano@outlook.com", "(206)429-2238");
        saveCaterer("Bradley Butt", "Lahore", "Council Bluffs, IA 51501", 1, 10, "harpes@hotmail.com", "(260)820-9928");
        saveCaterer("Izabela Ali", "Lahore", "420 E. Paris Hill Street", 1, 10, "rnewman@me.com", "(351)201-8026");
        saveCaterer("Zayd Summers", "Lahore", "Macungie, PA 18062", 1, 10, "barlow@mac.com", "(225)283-1174");
        saveCaterer("Mark Sheridan", "Lahore", "382 St Paul St.", 1, 10, "psichel@outlook.com", "(334)750-3656");
        saveCaterer("Danniella Galvan", "Lahore", "Avon, IN 46123", 1, 10, "anicolao@icloud.com", "(434)334-8005");
        saveCaterer("Saoirse Preston", "Lahore", "7573 Birch Hill Ave.", 1, 10, "karasik@aol.com", "(787)863-3265");
        saveCaterer("Robyn Stott", "Lahore", "Fremont, OH 43420", 1, 10, "cyrus@mac.com", "(804)747-1834");
        saveCaterer("Lianne Lang", "Lahore", "662 Tanglewood Street", 1, 10, "noneme@hotmail.com", "(806)353-6077");
        saveCaterer("Siyana Acosta", "Lahore", "North Attleboro, MA 02760", 1, 10, "ovprit@msn.com", "(787)822-7555");
        saveCaterer("Ciara Parker", "Lahore", "8 Windfall Drive", 1, 10, "jnolan@outlook.com", "(339)613-8183");
        saveCaterer("Dominik Wise", "Lahore", "Eugene, OR 97402", 1, 10, "oneiros@att.net", "(541)596-2492");
        saveCaterer("Denzel Bradshaw", "Lahore", "120 North Swanson Road", 1, 10, "dwheeler@aol.com", "(219)986-7394");
        saveCaterer("Kenneth Stein", "Lahore", "Nashua, NH 03060", 1, 10, "chrisk@outlook.com", "(551)666-3402");
        saveCaterer("Madelaine Salazar", "Lahore", "65 North Wagon St.", 1, 10, "gozer@comcast.net", "(561)835-9536");
        saveCaterer("Shakir Tanner", "Lahore", "Rockaway, NJ 07866", 1, 10, "denton@aol.com", "(701)333-7121");
        saveCaterer("Izzy Emerson", "Lahore", "36 Lawrence St.", 1, 10, "mugwump@yahoo.ca", "(224)804-1404");
        saveCaterer("Jean Sweet", "Lahore", "Mount Prospect, IL 60056", 1, 10, "ngedmond@att.net", "(641)243-3564");
        saveCaterer("Safiyah Whittle", "Lahore", "872 8th St.", 1, 10, "chrwin@comcast.net", "(216)271-5316");
        saveCaterer("Jordanna Mill", "Lahore", "Chaska, MN 55318", 1, 10, "panolex@verizon.net", "(276)356-9507");
        saveCaterer("Saqlain Vance", "Lahore", "186 Victoria Ave.", 1, 10, "ahmad@aol.com", "(323)344-6569");
        saveCaterer("Muhammad Bishop", "Lahore", "Wilkes Barre, PA 18702", 1, 10, "specprog@aol.com", "(206)429-2238");
        saveCaterer("Juniper Palacios", "Lahore", "82 Lafayette Street", 1, 10, "phyruxus@hotmail.com", "(260)820-9928");
        saveCaterer("William Newton", "Lahore", "Clover, SC 29710", 1, 10, "mpiotr@yahoo.ca", "(351)201-8026");
        saveCaterer("Iqra Flowers", "Lahore", "78 High Noon Road", 1, 10, "specprog@mac.com", "(225)283-1174");
        saveCaterer("Cobie East", "Lahore", "Superior, WI 54880", 1, 10, "psharpe@aol.com", "(334)750-3656");
        saveCaterer("Jeanne Mooney", "Lahore", "96 Homewood Avenue", 1, 10, "greear@optonline.net", "(434)334-8005");
        saveCaterer("Beth Wang", "Lahore", "Jamaica Plain, MA 02130", 1, 10, "mahbub@msn.com", "(787)863-3265");
        saveCaterer("Jeremy Kemp", "Lahore", "893 Silver Spear Street", 1, 10, "anicolao@msn.com", "(804)747-1834");
        saveCaterer("Jonty Patton", "Lahore", "Stow, OH 44224", 1, 10, "lpalmer@comcast.net", "(806)353-6077");
        saveCaterer("Murphy Cohen", "Lahore", "244 N. Kent Dr.", 1, 10, "jbryan@att.net", "(787)822-7555");
        saveCaterer("Darin Delgado", "Lahore", "Tewksbury, MA 01876", 1, 10, "phish@live.com", "(339)613-8183");
        saveCaterer("Abida Hartley", "Lahore", "330 High Ridge Ave.", 1, 10, "balchen@outlook.com", "(541)596-2492");
        saveCaterer("Willow Clements", "Lahore", "Goshen, IN 46526", 1, 10, "rogerspl@live.com", "(219)986-7394");
        saveCaterer("Izaak Legge", "Lahore", "97 South Proctor Ave.", 1, 10, "adhere@outlook.com", "(551)666-3402");
        saveCaterer("Isaiah Lancaster", "Lahore", "Woburn, MA 01801", 1, 10, "singer@me.com", "(561)835-9536");
        saveCaterer("Eman Dorsey", "Lahore", "109 North Livingston Avenue", 1, 10, "ilikered@yahoo.ca", "(701)333-7121");
        saveCaterer("Jo Gonzales", "Lahore", "Fort Worth, TX 76110", 1, 10, "drhyde@verizon.net", "(224)804-1404");
        saveCaterer("Jeevan Britt", "Lahore", "522 West Valley Farms Ave.", 1, 10, "budinger@me.com", "(641)243-3564");
        saveCaterer("Xavier Clarke", "Lahore", "Clearwater, FL 33756", 1, 10, "johnh@msn.com", "(216)271-5316");
        saveCaterer("Talha Nichols", "Lahore", "555 Thompson Court", 1, 10, "errxn@yahoo.com", "(276)356-9507");
        saveCaterer("Kayne Morton", "Lahore", "Copperas Cove, TX 76522", 1, 10, "zilla@optonline.net", "(323)344-6569");
        saveCaterer("Muhammad Bishopn", "Lahore", "802 East Pumpkin Hill Road", 1, 10, "padme@mac.com", "(206)429-2238");
        saveCaterer("Muhammad Bishop", "Lahore", "Charleston, SC 29406", 1, 10, "tbusch@hotmail.com", "(260)820-9928");
        saveCaterer("Muhammad Bishop", "Lahore", "6 Monroe St.", 1, 10, "ngedmond@aol.com", "(351)201-8026");
        saveCaterer("Muhammad Bishop", "Lahore", "Bettendorf, IA 52722", 1, 10, "giafly@verizon.net", "(225)283-1174");

        saveCaterer("name1", "city1", "street1", 1, 10, "test@test.com", "123123123");
        saveCaterer("name1", "city2", "street2", 1, 10, "test@test.com", "123123123");
        saveCaterer("name1", "city3", "street3", 1, 10, "test@test.com", "123123123");
        saveCaterer("name2", "city1", "street4", 1, 10, "test@test.com", "123123123");
        saveCaterer("name3", "city1", "street5", 1, 10, "test@test.com", "123123123");
        saveCaterer("name4", "city1", "street5", 1, 10, "test@test.com", "123123123");
        saveCaterer("name5", "city1", "street5", 1, 10, "test@test.com", "123123123");
        saveCaterer("name5", "city2", "street4", 1, 10, "test@test.com", "123123123");
        saveCaterer("name5", "city1", "street3", 1, 10, "test@test.com", "123123123");
        saveCaterer("name5", "city3", "street2", 1, 10, "test@test.com", "123123123");

    }

    private void saveCaterer(String name,
                             String city,
                             String street,
                             int minGuestNo,
                             int maxGuestNo,
                             String email, String mobileNumber) {
        Caterer caterer = Caterer.builder()
                .name(name)
                .address(Address.builder().cityName(city).streetAddress(street).build())
                .capacity(Capacity.builder().minGuestNo(minGuestNo).maxGuestNo(maxGuestNo).build())
                .contact(Contact.builder().email(email).mobileNumber(mobileNumber).build())
                .build();
        catererRepository.save(caterer);
    }
}
