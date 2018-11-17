
package risk;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import static risk.Main.g;

public class RiskMap {
    static private Image image = Toolkit.getDefaultToolkit().getImage("./riskMap.jpg");
    static private ArrayList<Country> countries = new ArrayList<Country>();
    
    static public void draw(int x, int y, Main frame) {
        g.drawImage(image, 0, 0, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, frame);
        Button.drawBack(frame, 0, Window.YTITLE, x, y);
    }
    
    static public Country contains(int x, int y) {
        for (Country country : countries) {
            if (country != null && country.boundary.contains(x, y))
                return(country);
        }
        return(null);
    }
    
    static public void mouseInCountryFunction(int x, int y) {
        Country country = contains(x, y);
        country.mouseInCountry();
    }
         
    RiskMap() {
        Polygon countryBoundry;
        String countryName;
        Country country;
        
        // Initializing countries 
        // Alaska
        { int x[] = { 31,30,86,125,125,89 };
        int y[] = { 180,81,69,93,170,168 };
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Alaska";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Greenland
        { int x[] = { 412,451,463,487,495,563,574,566,579,480 };
        int y[] = { 62,122,185,196,152,114,77,61,30,30 };
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Greenland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Mexico
        { int x[] = { 159,243,283,281,240,176,154,158,203 };
        int y[] = { 338,372,387,402,422,410,353,339,342 };
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Mexico";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Guatamala
        { int x[] = { 238,282,285,280,243,238,281 };
        int y[] = { 422,399,452,468,467,422,400 };
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Guatamala";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Western United States
        { int x[] = {155,222,218,158,142,158};
        int y[] = {339,338,238,236,279,333};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western United States";
        country = new Country(countryBoundry, countryName);
        countries.add(country); 
        
        // Brazil
        { int x[] = {431,446,506,529,507,440,417,397,415,393,398,355,329,310,334,335,353,387,390,432};
        int y[] = {473,495,511,549,609,685,690,666,646,624,585,546,553,533,519,502,494,480,496,477};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Brazil";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Peru
        { int x[] = {274,327,327,311,312,329,323,293,275,255,249,258,282};
        int y[] = {494,512,520,524,541,546,598,607,586,556,522,484,494};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Peru";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Southern South America
        { int x[] = {297,333,377,405,424,376,346,338,304};
        int y[] = {395,391,414,418,427,431,427,404,398};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Caribbean Islands";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // North West Canada
        { int x[] = {126,251,247,203,126,127};
        int y[] = {162,164,128,85,93,161};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "North West Canada";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Iceland
        { int x[] = {574,619,621,576,560,564};
        int y[] = {144,156,179,185,168,145};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iceland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // British Isles
        { int x[] = {579,597,584,541,518,561};
        int y[] = {213,282,305,305,268,210};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "British Isles";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Svalbard
        { int x[] = {621,654,696,713,616};
        int y[] = {42,109,91,28,27};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Svalbard";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Northern Europe
        { int x[] = {669,675,734,750,733,648,649,614,658,661};
        int y[] = {218,243,240,288,310,328,312,293,252,222};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern Europe";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Western Europe
        { int x[] = {539,539,588,614,612,640,648,642,613,594,567,588,587};
          int y[] = {369,410,436,403,387,361,328,313,295,316,320,345,368};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western Europe";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Southern Europe
        { int x[] = {683,664,643,648,729,759,731,732,686};
          int y[] = {423,410,356,329,308,344,395,420,419};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Southern Europe";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Ukraine 
        { int x[] = {750,777,782,791,788,816,816,785,749,736,751,716,748};
          int y[] = {203,257,250,263,280,289,315,348,341,310,289,218,199};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Ukraine";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Ontario
        { int x[] = {248,303,310,348,351,377,329,325,309,297,250,251,301};
          int y[] = {159,162,192,196,251,263,280,253,235,245,233,161,160};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Ontario";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Western Australia
        { int x[] = { 1138,1137,1078,1031,1103,1135};
          int y[] = {663,771,790,714,656,658};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western Australia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Queensland
        { int x[] = {1137,1137,1195,1198,1262,1231,1203,1135};
          int y[] = { 662,731,731,748,745,662,642,648};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Queensland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Victoria
        { int x[] = {1139,1197,1197,1261,1246,1227,1138,1139};
          int y[] = {732,731,747,744,803,829,771,731};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Victoria";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Sweden
        { int x[] = {712,749,761,725,719,725,729,718,703,704,710,693,673,654,649,711,739};
          int y[] = {119,130,180,200,183,164,155,151,180,193,197,234,207,213,182,116,121};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Sweden";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Alergia
        { int x[] = {583,605,641,674,679,676,687,653,596,574,586};
          int y[] = {440,437,423,423,441,465,496,516,480,475,440};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Alergia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Libya
        { int x[] = {681,725,727,751,754,710,690,680,684};
          int y[] = {447,461,446,457,518,500,500,468,444};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Libya";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // North Africa
        { int x[] = {573,555,556,573,597,653,683,713,709,747,743,749,711,687,648,572,556};
          int y[] = {476,507,548,578,597,585,608,609,587,572,543,522,501,499,521,475,510};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "North Africa";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Congo
        { int x[] = {712,749,787,791,780,786,784,747,741,699,682,682,714,711,745};
          int y[] = {589,574,602,618,640,664,697,684,665,659,637,613,614,588,575};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Congo";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // South Africa
        { int x[] = {699,738,747,783,782,795,807,837,840,809,768,730,693,702,699,728};
          int y[] = {661,665,682,695,669,675,687,681,702,758,813,820,719,685,661,667};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "South Africa";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Eastern Africa
        { int x[] = {789,815,802,825,856,894,884,861,838,839,837,809,792,783,790};
          int y[] = {607,600,581,534,572,564,600,626,652,677,683,690,668,637,606};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Eastern Africa";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Sudan
        { int x[] = {788,813,802,826,817,756,745,746,755,772,785,790};
          int y[] = {607,602,582,533,512,508,544,561,575,595,600,608};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Sudan";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Egypt
        { int x[] = {755,816,794,803,801,752,755,813};
          int y[] = {507,512,469,475,457,456,507,510};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Egypt";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Saudi Arabia
        { int x[] = {803,855,924,948,924,907,893,884,858,828,833,805,810,823};
          int y[] = {471,565,538,493,463,473,460,447,441,452,456,465,479,505};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Saudi Arabia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Iraq
        { int x[] = {803,832,826,859,882,881,864,859,813,809,802,801,806,831,829};
          int y[] = {466,455,451,441,443,425,412,407,414,421,421,453,467,457,450};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iraq";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Iran
        { int x[] = {829,865,866,880,879,916,941,947,930,939,932,881,868,872,876,871,830};
          int y[] = {356,401,417,424,439,461,463,445,429,409,391,393,382,371,368,363,355};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iran";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Afghanistan
        { int x[] = {935,942,931,946,938,943,974,986,971,980,995,990,1010,993,982,976,960};
          int y[] = {395,406,431,446,456,464,474,470,450,443,392,375,362,365,358,369,371};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Afghanistan";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Kazakhstan
        { int x[] = {873,885,872,905,936,961,981,1001,985,1024,986,934,928,895,875,879};
          int y[] = {281,318,327,392,393,371,357,358,334,281,252,252,270,267,277,297};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Kazakhstan";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Turkey
        { int x[] = {750,837,866,865,812,809,801,751,742,751,768,794};
          int y[] = {367,371,399,413,412,421,422,415,389,366,374,368};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Turkey";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Volga
        { int x[] = {803,869,854,878,871,924,909,859,775,774,763,784,792,788,818,817,805};
          int y[] = {341,360,312,299,275,268,181,205,205,218,232,248,259,279,290,313,337};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Volga";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Northern Russia
        { int x[] = {765,773,844,851,857,909,909,877,774,750,752,756,750,762,774,807,830};
          int y[] = {227,205,201,190,201,179,138,124,130,124,157,177,195,227,205,203,201};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern Russia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Novada Zendya
        { int x[] = {860,856,911,884,860,838,827,821,831,849};
          int y[] = {121,102,46,42,55,72,87,105,119,124};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Novada Zendya";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Urd
        { int x[] = {911,911,909,913,915,925,932,965,970,988,994,991,1001,999,979,957,939};
          int y[] = {102,136,172,203,218,263,250,243,249,247,241,229,226,171,156,121,104};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Urd";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Siberia
        { int x[] = {944,983,1001,1001,987,1025,1045,1067,1066,1039,1077,1066,1082,1060,1050,973};
          int y[] = {105,154,173,226,248,279,264,270,245,232,180,141,125,87,70,80};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Siberia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        // Yakutta
        { int x[] = {1067,1076,1082,1090,1107,1134,1168,1192,1244,1226,1157,1078,1063,1083,1068,1077};
          int y[] = {144,180,200,201,186,208,206,162,103,90,82,79,90,123,140,180};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Yakutta";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Irkatsk
        { int x[] = {1075,1080,1091,1108,1131,1142,1135,1142,1113,1084,1065,1045,1051,1059,1068,1072};
          int y[] = {180,199,200,188,203,224,228,246,264,264,251,235,216,210,214,183};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Irkatsk";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Amer
        { int x[] = {1192,1205,1191,1199,1215,1224,1234,1232,1222,1209,1202,1206,1190,1167,1157,1141,1134};
          int y[] = {162,179,189,204,215,214,221,254,275,278,261,239,243,239,222,224,207};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Amer";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Kamchakta 
        { int x[] = {1193,1208,1242,1257,1261,1283,1290,1289,1323,1349,1356,1309,1273,1251,1243,1219,1219};
          int y[] = {161,186,177,181,211,225,229,170,144,150,121,98,93,94,109,129,140};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Kamchakta";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Japan
        { int x[] = {1222,1291,1297,1241,1251,1237,1210,1221,1240};
          int y[] = {388,328,237,226,278,318,349,384,379};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Japan";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // China
        { int x[] = {1109,1163,1170,1212,1195,1224,1205,1214,1203,1166,1156,1135,1142,1155,1086,1078,1064,1072,1101,1105,1107};
          int y[] = {446,448,466,394,346,327,302,280,243,238,221,225,243,285,313,316,371,382,383,419,445};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "China";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Mongolia
        { int x[] = {1066,1081,1134,1139,1159,1153,1081,1049,1027,1046,1056,1069,1065,1084};
          int y[] = {255,264,250,263,263,289,315,313,280,264,272,271,253,261};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Mongolia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Xiajano
        { int x[] = {1025,1047,1048,1068,1078,1066,1012,996,986,1008,1014,1025};
          int y[] = {280,302,311,310,319,367,362,350,330,316,295,280};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Xiajano";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Xijang
        { int x[] = {1014,1062,1070,1101,1105,1087,1081,1066,1013,1014,1063};
          int y[] = {362,367,382,382,423,426,412,420,390,364,367};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Xijang";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // India
        { int x[] = {1013,1013,1065,1071,1082,1088,1090,1085,1055,1043,1042,1007,969,975,985,971,996,989,1012,1017,1017};
          int y[] = {361,392,420,415,413,430,476,465,486,519,579,573,494,472,470,451,392,375,364,378,393};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "India";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Thailand
        { int x[] = {1087,1104,1108,1163,1157,1173,1173,1162,1146,1155,1139,1119,1112,1082,1092,1083,1095,1107,1108,1118,1151};
          int y[] = {425,421,447,449,468,477,503,514,518,543,556,537,508,497,473,437,420,428,442,452,446};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Thailand";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Philippines
        { int x[] = {1221,1243,1273,1281,1263,1236,1210,1200,1211,1213,1218,1235};
          int y[] = {446,446,489,516,532,533,528,504,482,460,445,441};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Philippines";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Indonesia
        { int x[] = { 1063,1099,1112,1177,1204,1225,1234,1226,1099,1063,1055};
          int y[] = {536,539,564,539,561,588,620,638,627,562,543};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Indonesia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // New Guinea
        { int x[] = {1241,1251,1279,1320,1353,1362,1333,1302,1259,1238,1236,1241};
          int y[] = {584,607,635,645,645,598,591,571,567,569,586,600};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "New Guinea";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Hawaii
        { int x[] = {24,98,74,61,31,11,29};
          int y[] = {303,335,364,343,330,306,300};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Hawaii";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // New Zealand
        { int x[] = {1308,1314,1282,1265,1281,1312,1344,1358,1324,1305};
          int y[] = {688,731,771,790,807,790,747,707,681,679};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "New Zealand";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Venezuela
        { int x[] = {277,327,339,361,386,391,433,434,329,297,284,280,287,276,291,328};
          int y[] = {494,517,499,492,481,496,477,453,431,429,454,468,480,492,494,514};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Venezuela";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Bolivia
        { int x[] = {327,326,334,367,372,385,390,398,397,386,383,358,355,327};
          int y[] = {554,593,627,622,607,606,613,600,582,582,567,562,545,554};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Bolivia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Argentina
        { int x[] = { 326,338,363,372,389,413,396,417,402,381,370,406,372,318,276,294,310,325,335};
          int y[] = { 601,621,622,607,606,645,667,687,718,745,783,816,831,806,745,664,613,594,622};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Argentina";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Falkland Islands
        { int x[] = {434,398,386,400,430,461,482,477,456};
          int y[] = {743,742,765,790,801,786,765,742,739};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Falkland Islands";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Southern United States
        { int x[] = {224,224,208,216,225,243,265,294,326,341,352,381,377,373,344,294,222};
          int y[] = {309,339,340,357,350,371,360,360,365,387,345,323,300,294,294,311,306};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Southern United States";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Midwestern United States
        { int x[] = {224,220,281,299,332,344,342,294,224,219};
          int y[] = {305,240,237,244,270,281,291,309,307,238};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Midwestern United States";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Northern United States
        { int x[] = {345,345,378,405,395,384,362,343};
          int y[] = {285,294,298,267,247,262,266,281};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern United States";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Alberta
        { int x[] = {156,248,249,126,155};
          int y[] = {233,235,163,162,236};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Alberta";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Quebec
        { int x[] = {354,352,376,394,406,437,475,460,425,396,370,363,369,354};
          int y[] = {199,253,264,250,275,256,243,204,164,150,147,162,189,196};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Quebec";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Nunavut
        { int x[] = {251,301,309,324,359,363,332,345,210,181,212,246,249,301};
          int y[] = {161,160,144,144,118,97,89,40,34,64,89,126,162,161};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Nunavut";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Madagascar
        { int x[] = {903,906,894,859,828,848,873,892};
          int y[] = {683,720,767,788,770,714,697,682};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Madagascar";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Buffalo Islands
        { int x[] = {371,424,433,385,346,333,380,365,424};
          int y[] = {135,148,113,48,50,86,99,132,148};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Buffalo Islands";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Soverya
        { int x[] = {935,950,988,1036,1042,947};
          int y[] = {31,58,66,52,29,28};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Soverya";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Novashelkye
        { int x[] = {1131,1136,1161,1194,1196,1137};
          int y[] = {32,63,80,69,28,28};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Novashelkye";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
    }
    
    static public ArrayList<Country> getCountryList()
    { return(countries); }
}
