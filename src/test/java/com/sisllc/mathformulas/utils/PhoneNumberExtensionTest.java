/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisllc.mathformulas.utils;

import com.abc.utils.DomainUtils;
import static com.abc.utils.DomainUtils.extractUsPhoneExtension;
import com.abc.utils.PhoneNumberExtensionVO;
import com.spring5.ProjectTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author david
 */
public class PhoneNumberExtensionTest  extends ProjectTest {
    private static final Logger log = LoggerFactory.getLogger(PhoneNumberExtensionTest.class);
        
    private static final String CIM_PHONE_REG_EX = "^(\\(\\d{3}\\))[ ]\\d{3}-\\d{4}$";
    private static final Pattern pattern = Pattern.compile(CIM_PHONE_REG_EX);

    @Test
    public void testValidatePhones() {
        String phone = "(231) 734-6552";
        Matcher matcher = pattern.matcher(phone);
        assertTrue(matcher.find());
        
        phone = "(231)734-6552";
        matcher = pattern.matcher(phone);
        assertTrue(!matcher.find());
        
        phone = "(734) 734-6552";
        matcher = pattern.matcher(phone);
        assertTrue(matcher.find());
        
        phone = "(734)  734-6552";
        matcher = pattern.matcher(phone);
        assertTrue(!matcher.find());
    }

    @Test
    public void formatPhones() {
        Map<String, String> map = new HashMap();
        map.put("2317346552", "(231) 734-6552");
        map.put("8106068400", "(810) 606-8400");
        map.put("9897936674", "(989) 793-6674");

        for (String key : map.keySet()) {
            String formattedPohone = DomainUtils.phoneNumberToUSFormat(key);
            assertEquals(formattedPohone, map.get(key));
        }
    }

    @Test
    public void extractPhones() {
        List<String> list = new ArrayList();
        list.add("(810) 233-4400");
        list.add("2317346552");
        list.add("810-606-8400 ");
        list.add("(989)793-6674");
        list.add("231-728-4353 X 3105");
        list.add("1-800-558-2797, ext.");
        list.add("989 832-5400 x 128");
        list.add("866-200-8877 ext3");
        list.add("734-729-3100 EXT 124");
        list.add("989-832-5400 Ext 2");
        list.add("574-347-8950  xt 707");
        list.add("866-716-3257 EX.7661");
        list.add("906-855-676-7147");
        list.add("231347-4800");
        list.add("06	186	26494	BAURD	D");
        list.add("800-215-4206 X 0565");
        list.add("231-728-4353 x3100");
        list.add("248-537-3300 ext3803");
        list.add("800 860 4230 EXT 135");
        list.add("989-832-5400 ext 128");
        list.add("231-689-2100 x107");
        list.add("866-716-3257 x 7517");
        list.add("866-716-3257 ext7015");
        list.add("616-453-7719 ext 110");
        list.add("248-601-0777 opt #2");
        list.add("517-782-6054 ext 236");
        list.add("269-382-0515 -X 103");
        list.add("248-594-4574ext18706");
        list.add("800-860-4230-12135");
        list.add("1-800-558-2797, ext.");
        list.add("866-716-3257 7623");
        list.add("866-716-3257 X 7595");
        list.add("1-800-283-8934");
        list.add("231-627-2031-2103");
        list.add("800-689-");
        list.add("1-800-558-2797, ext.");
        list.add("269-382-0515 x 318");
        list.add("231-728-4353 x 3100");
        list.add("(810)547-1763");
        list.add("800 645-2060 ext 711");
        list.add("989) 673-4121(");
        list.add("248-601-0777 EXT 2");
        list.add("231-932-.3063");
        list.add("2489120630");
        list.add("456132789");
        list.add("866-716-3257ext 7595");
        list.add("ehedenhaven@gmail.co");
        list.add("248539-9151");
        list.add("9895456000");
        list.add("248-5931391");
        list.add("248.996.1049 ");
        list.add("382-0515 x105");
        list.add("855-206-5924 xt12954");
        list.add("866 716-3257 Ext7015");
        list.add("517-205-6995 opt 2");
        list.add("(989) 652-4286");
        list.add("(877)447-0113");
        list.add("1-269-651-4500");
        list.add("989-832-5400x128");
        list.add("855-224-5810 X1003");
        list.add("866-735-0921 ext 104");
        list.add("sheilapohl@gmail.com");
        list.add("574-276-9163 Cell");
        list.add("734-729-3100 ext 108");
        list.add("810-987-8811 x111");
        list.add("810-989-1385 x2036");
        list.add("734-729-3100 x104");
        list.add("231-9323063");
        list.add("(231) 439-9222");
        list.add("616-247-1312 X318");
        list.add("800-451-0525 1367");
        list.add("248-669-5330 option3");
        list.add("269-927-8635 xt304");
        list.add("231-972-2884 ");
        list.add("231-929-7083 x 2202");
        list.add("800-922-5101 x2907");
        list.add("269659-4526");
        list.add("800-922-5101 ext 290");
        list.add("734-222-6076 207");
        list.add("888-557-4462 x5376");
        list.add("1 855 206-5924 12129");
        list.add("989) 865-8100");
        list.add("800-6452060");
        list.add("989345-7801");
        list.add("800-860-4230-12122");
        list.add("616-247-3900 EXT/ 31");
        list.add("733-8637");
        list.add("855-206-5924 12954");
        list.add("269-382-0515 ext 317");
        list.add("269-962-1750 x148");
        list.add("922-1377");
        list.add("586-752-1111 ext 119");
        list.add("1-800-558-2797, ext");
        list.add("269-471-1100 xt6");
        list.add("hstockford@leelinhom");
        list.add("855-206-5924x12052");
        list.add("(269) 883-6002x1003");
        list.add("517-205-6995 opt 1");
        list.add("989-832-5400 ext. 12");
        list.add("AMNTELEVISION@YAHOO.");
        list.add("866-716-3257  7623");
        list.add("1-800-558-2797, ext.");
        list.add("1-800-558-2797, ext.");
        list.add("1-800-558-2797, ext.");
        list.add("269-445-8110  XT 222");
        list.add("1-800-558-2797, ext.");
        list.add("mike@myhomecarellc.c");
        list.add("269-382-0515 x 119");
        list.add("(269) 279-8083");
        list.add("989-832-5400x805");
        list.add("269-382-0515 x 119");
        list.add("231-631-6567  cell");
        list.add("1-800-558-2797, ext.");
        list.add("7158472000 ext52586");
        list.add("508-988-1132(direct)");
        list.add("855-206-5924x12954");
        list.add("877-456-1787  x4642");
        list.add("");
        list.add(" ");
        list.add("null");

        for (String phoneExtension : list) {
            PhoneNumberExtensionVO phoneNumberExtensionVO = extractUsPhoneExtension(phoneExtension);
            if (phoneNumberExtensionVO.isValid()) {
                assertTrue(StringUtils.isNotEmpty(phoneNumberExtensionVO.getPhoneNumber()));
                //System.out.println("" + phoneNumberExtensionVO);
            } else {
                assertTrue(StringUtils.isEmpty(phoneNumberExtensionVO.getPhoneNumber()));
                System.out.println("\n ****** " + phoneNumberExtensionVO + " ****** \n ");
            }
        }
    }
}
