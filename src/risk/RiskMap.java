
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import static risk.Main.g;

public class RiskMap {
    static private Image map = Toolkit.getDefaultToolkit().getImage("./riskMap.jpg");
    static private ArrayList<Country> countries = new ArrayList<Country>();
    
    static public void draw(Main frame, int x, int y) {
        // Draws map
        g.drawImage(map, 0, 0, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, frame);
        // Draws troop counters
        Country.drawAllTroopCounters();
        // Draws back button
        Button.drawBack(frame, 0, Window.YTITLE, x, y);
        // Draws current country name by mouse pointer
//        if(Country.getCountryOnMouse()!=null)
//        System.out.println(Country.getCountryOnMouse().getName());
        if (Country.getCountryOnMouse() != null)
            Country.getCountryOnMouse().drawNameOnMouse(x, y);
    }
    
    static public Country contains(int x, int y) {
        for (Country country : countries) {
            if (country != null && country.getBoundary().contains(x, y)) {
                return country;
            }
        }
        return null;
    }
    
    static public void mouseInCountryHandler(int x, int y) {
        if (Country.getCountryOnMouse() != null)
            Country.getCountryOnMouse().mouseInCountry();
    }
         
    RiskMap() {
        Polygon countryBoundry;
        String countryName;
        Country country;
        
        // Initializing countries 
        // Alaska
        { int x[] = {122,103,98,98,88,79,77,77,68,68,57,43,33,27,26,36,42,48,47,36,34,29,23,23,30,38,47,50,49,40,31,30,31,39,41,58,48,86,125,125};
          int y[] = {167,165,157,157,169,169,156,156,161,168,174,183,184,185,180,177,170,165,158,158,157,154,148,143,137,135,134,130,125,123,123,118,113,109,108,110,93,77,91,160};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Alaska";
        country = new Country(countryBoundry, countryName, 64,100);
        countries.add(country);
        
        // Greenland
        { int x[] = { 412,451,463,487,495,563,574,566,579,480 };
        int y[] = { 62,122,185,196,152,114,77,61,30,30 };
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Greenland";
        country = new Country(countryBoundry, countryName, 470,97);
        countries.add(country);
        
        // Mexico
        { int x[] = {158,170,180,189,197,203,206,209,214,222,227,230,233,241,239,247,252,259,266,268,284,285,274,268,253,251,255,239,224,216,206,203,204,204,198,191,188,181,178,180,185,185,190,190,185,178,176,169,171,169,161,157,159};
          int y[] = {338,339,343,347,347,342,347,351,356,354,353,362,369,371,382,396,399,399,395,390,388,398,403,405,405,409,415,425,418,416,414,408,400,397,384,374,358,350,349,359,374,387,401,407,406,400,388,379,365,354,347,342,343};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Mexico";
        country = new Country(countryBoundry, countryName, 157,349);
        countries.add(country);
        
        // Guatamala
        { int x[] = {284,273,277,267,284,260,253,252,247,243,240,247,248,255,256,258,265,270,279,281,285};
          int y[] = {455,443,422,415,400,404,407,418,418,419,422,432,440,447,455,458,463,464,468,470,454};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Guatamala";
        country = new Country(countryBoundry, countryName, 241,424);
        countries.add(country);
        
        // Western United States
        { int x[] = {217,218,226,227,220,223,194,179,171,163,161,155,150,146,145,146,148,151,152,153,158,216};
          int y[] = {239,279,283,306,306,338,346,343,339,338,338,323,314,303,293,282,274,266,257,246,237,238};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western United States";
        country = new Country(countryBoundry, countryName, 163,260);
        countries.add(country); 
        
        // Brazil
        { int x[] = {419,442,441,464,481,494,496,521,519,497,474,459,428,439,432,412,394,387,364,370,363,357,353,344,340,333,332,325,318,311,309,319,329,329,349,354,356,369,381,390,388,395,399,399,392,392,398,406,409,415,412,397,411};
          int y[] = {688,664,646,632,632,611,585,555,539,518,516,506,500,492,476,492,500,484,489,495,504,502,497,497,505,505,514,522,522,528,537,542,550,554,548,544,557,561,567,576,581,583,594,605,610,617,623,623,635,640,652,666,680};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Brazil";
        country = new Country(countryBoundry, countryName, 417,562);
        countries.add(country);
        
        // Peru
        { int x[] = {321,327,330,317,308,315,325,328,300,291,289,274,262,259,291};
        int y[] = {602,592,545,546,533,523,523,513,508,500,495,495,512,532,584};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Peru";
        country = new Country(countryBoundry, countryName, 271,545);
        countries.add(country);
        
        // Caribbean Islands
        { int x[] = {297,333,377,405,424,376,346,338,304};
        int y[] = {395,391,414,418,427,431,427,404,398};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Caribbean Islands";
        country = new Country(countryBoundry, countryName, 333,394);
        countries.add(country);
        
        // North West Canada
        { int x[] = {126,249,248,234,219,211,210,206,191,182,169,164,157,148,138,128,125,127};
          int y[] = {162,162,132,122,115,108,92,85,87,90,95,96,97,97,91,91,103,159};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "North West Canada";
        country = new Country(countryBoundry, countryName, 164,101);
        countries.add(country);
        
        // Iceland
        { int x[] = {574,619,621,576,560,564};
        int y[] = {144,156,179,185,168,145};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iceland";
        country = new Country(countryBoundry, countryName, 570,146);
        countries.add(country);
        
        // British Isles
        { int x[] = {541,559,573,585,589,594,594,589,586,582,579,583,578,574,562,561,562,559,550,549,540,531,526,522,528,537,548,551,552,543};
          int y[] = {304,304,303,302,302,283,282,277,267,252,238,228,216,212,217,236,250,255,249,242,242,250,258,268,280,281,282,282,288,301};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "British Isles";
        country = new Country(countryBoundry, countryName, 534,252);
        countries.add(country);
        
        // Svalbard
        { int x[] = {657,664,666,671,681,685,697,691,683,677,686,698,708,713,709,662,661,651,640,624,620,623,633,639,647,652};
          int y[] = {107,81,68,69,85,93,89,77,63,57,48,50,43,34,27,27,36,35,33,37,46,60,72,91,103,108};

        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Svalbard";
        country = new Country(countryBoundry, countryName, 632,33);
        countries.add(country);
        
        // Northern Europe
        { int x[] = {650,667,689,691,698,704,709,718,721,730,735,740,751,745,746,740,740,731,714,706,699,685,679,673,669,670,663,662,658,657,657,655,650,642,639,631,626,623,620,617,621,632,638,646,650};
          int y[] = {326,329,324,318,314,314,314,307,307,307,306,297,293,269,256,252,243,240,241,240,241,244,245,245,245,218,218,228,234,246,253,255,259,264,269,273,280,287,292,292,303,308,311,311,318};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern Europe";
        country = new Country(countryBoundry, countryName, 661,251);
        countries.add(country);
        
        // Western Europe
        { int x[] = {539,539,588,614,612,640,648,642,613,594,567,588,587};
          int y[] = {369,410,436,403,387,361,328,313,295,316,320,345,368};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western Europe";
        country = new Country(countryBoundry, countryName, 585,318);
        countries.add(country);
        
        // Southern Europe
        { int x[] = {642,652,652,665,674,679,684,684,674,667,670,678,685,690,695,702,706,695,686,675,678,694,701,707,711,714,724,727,734,735,735,731,737,741,742,744,752,753,756,760,761,751,750,749,739,731,729,722,714,713,710,702,695,689,675,670,663,659,651,648,640,636,643};
          int y[] = {356,358,376,379,385,389,399,407,409,409,417,418,420,411,403,396,389,383,370,363,357,365,373,382,394,401,418,420,420,413,399,396,393,388,382,378,371,369,360,349,340,339,331,319,316,311,305,304,304,314,314,315,312,324,325,326,329,329,328,327,340,340,353};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Southern Europe";
        country = new Country(countryBoundry, countryName, 654,344);
        countries.add(country);
        
        // Ukraine 
        { int x[] = {746,737,734,744,745,753,758,770,776,776,780,785,789,794,799,794,802,807,809,814,816,817,820,811,802,801,790,786,784,784,793,793,792,790,786,787,774,772,772,763,761,761,760,758,755,752,747,736,729,736,734,729,726,719,719,728,732,742,746,750};
          int y[] = {296,304,313,316,327,340,342,329,328,338,342,346,346,341,338,330,326,324,322,308,299,298,290,287,284,277,278,278,270,270,268,268,260,258,258,247,251,246,246,240,237,231,220,216,211,204,202,201,211,214,228,219,216,216,229,234,246,255,270,290};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Ukraine";
        country = new Country(countryBoundry, countryName, 757,280);
        countries.add(country);
        
        // Ontario
        { int x[] = {248,303,310,348,351,377,329,325,309,297,250,251,301};
          int y[] = {159,162,192,196,251,263,280,253,235,245,233,161,160};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Ontario";
        country = new Country(countryBoundry, countryName, 253,165);
        countries.add(country);
        
        // Western Australia
        { int x[] = {1137,1139,1121,1115,1106,1095,1085,1081,1075,1073,1071,1078,1067,1063,1061,1061,1062,1076,1089,1094,1097,1099,1106,1109,1112,1116,1122,1128,1131,1132};
          int y[] = {669,765,769,777,779,781,782,782,781,776,766,767,742,739,725,714,705,697,697,695,690,685,680,674,669,663,662,664,667,667};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western Australia";
        country = new Country(countryBoundry, countryName, 1066,690);
        countries.add(country);
        
        // Queensland
        { int x[] = {1138,1137,1198,1197,1260,1256,1250,1244,1237,1233,1222,1219,1218,1217,1211,1207,1203,1195,1194,1191,1184,1181,1175,1173,1169,1163,1155,1152,1146,1140};
          int y[] = {666,729,732,747,747,728,720,713,705,697,692,683,676,669,663,654,644,676,682,684,679,670,661,655,653,652,651,651,656,661};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Queensland";
        country = new Country(countryBoundry, countryName, 1147,649);
        countries.add(country);
        
        // Victoria
        { int x[] = {1140,1198,1197,1258,1256,1253,1249,1244,1243,1235,1234,1232,1232,1231,1227,1220,1217,1211,1205,1199,1193,1189,1187,1183,1179,1177,1177,1174,1168,1165,1157,1151,1145,1143,1139,1138,1137,1137,1137,1137,1139};
          int y[] = {733,731,746,747,760,762,774,784,789,798,798,807,815,819,823,822,814,804,804,803,801,792,786,782,775,771,771,767,767,767,767,766,764,764,764,761,752,741,738,734,732};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Victoria";
        country = new Country(countryBoundry, countryName, 1143,736);
        countries.add(country);
        
        // Sweden
        { int x[] = {725,750,761,752,752,753,750,750,736,731,727,723,715,704,700,693,687,681,675,671,662,656,651,651,651,655,670,675,677,681,684,688,692,696,709,710,708,703,710,716,722,731,726,720,719,719,722,726};
          int y[] = {197,190,177,170,162,153,129,127,126,124,121,118,116,124,130,136,147,157,168,171,177,181,189,197,214,219,205,205,222,233,237,238,238,231,212,199,191,180,173,160,156,155,166,174,178,186,193,195};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Sweden";
        country = new Country(countryBoundry, countryName, 698,131);
        countries.add(country);
        
        // Algeria
        { int x[] = {583,605,641,674,679,676,687,653,596,574,586};
          int y[] = {440,437,423,423,441,465,496,516,480,475,440};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Algeria";
        country = new Country(countryBoundry, countryName, 613,436);
        countries.add(country);
        
        // Libya
        { int x[] = {681,725,727,751,754,710,690,680,684};
          int y[] = {447,461,446,457,518,500,500,468,444};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Libya";
        country = new Country(countryBoundry, countryName, 693,454);
        countries.add(country);
        
        // North Africa
        { int x[] = {573,593,649,692,703,710,750,749,745,745,750,751,745,736,721,714,710,712,712,698,688,681,677,665,656,649,628,614,600,590,586,577,572,567,560,556,555,557,554,570};
          int y[] = {474,477,518,497,502,501,521,540,548,553,562,566,571,576,581,585,587,606,608,609,609,606,594,596,584,586,592,596,598,595,591,584,578,569,558,550,541,529,507,477};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "North Africa";
        country = new Country(countryBoundry, countryName, 560,507);
        countries.add(country);
        
        // Congo
        { int x[] = {712,749,787,791,780,786,784,747,741,699,682,682,714,711,745};
          int y[] = {589,574,602,618,640,664,697,684,665,659,637,613,614,588,575};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Congo";
        country = new Country(countryBoundry, countryName, 718,595);
        countries.add(country);
        
        // South Africa
        { int x[] = {699,738,747,783,782,795,807,837,840,809,768,730,693,702,699,728};
          int y[] = {661,665,682,695,669,675,687,681,702,758,813,820,719,685,661,667};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "South Africa";
        country = new Country(countryBoundry, countryName, 722,703);
        countries.add(country);
        
        // Eastern Africa
        { int x[] = {789,815,802,825,856,894,884,861,838,839,837,809,792,783,790};
          int y[] = {607,600,581,534,572,564,600,626,652,677,683,690,668,637,606};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Eastern Africa";
        country = new Country(countryBoundry, countryName, 813,615);
        countries.add(country);
        
        // Sudan
        { int x[] = {788,813,802,826,817,756,745,746,755,772,785,790};
          int y[] = {607,602,582,533,512,508,544,561,575,595,600,608};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Sudan";
        country = new Country(countryBoundry, countryName, 760,519);
        countries.add(country);
        
        // Egypt
        { int x[] = {755,816,794,803,801,752,755,813};
          int y[] = {507,512,469,475,457,456,507,510};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Egypt";
        country = new Country(countryBoundry, countryName, 782, 464);
        countries.add(country);
        
        // Saudi Arabia
        { int x[] = {805,822,833,838,842,849,855,861,885,898,906,905,918,925,933,943,940,931,925,924,916,908,897,892,889,886,882,858,829,834,807};
          int y[] = {471,501,513,519,530,542,556,561,553,548,543,539,536,525,515,494,482,476,470,465,474,474,474,464,458,451,447,442,449,459,469};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Saudi Arabia";
        country = new Country(countryBoundry, countryName, 894,479);
        countries.add(country);
        
        // Iraq
        { int x[] = {803,832,826,859,882,881,864,859,813,809,802,801,806,831,829};
          int y[] = {466,455,451,441,443,425,412,407,414,421,421,453,467,457,450};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iraq";
        country = new Country(countryBoundry, countryName, 820,414);
        countries.add(country);
        
        // Iran
        { int x[] = {829,865,866,880,879,916,941,947,930,939,932,881,868,872,876,871,830};
          int y[] = {356,401,417,424,439,461,463,445,429,409,391,393,382,371,368,363,355};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iran";
        country = new Country(countryBoundry, countryName, 870,367);
        countries.add(country);
        
        // Afghanistan
        { int x[] = {935,942,931,946,938,943,974,986,971,980,995,990,1010,993,982,976,960};
          int y[] = {395,406,431,446,456,464,474,470,450,443,392,375,362,365,358,369,371};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Afghanistan";
        country = new Country(countryBoundry, countryName, 947,384);
        countries.add(country);
        
        // Kazakhstan
        { int x[] = {873,885,872,905,936,961,981,1001,985,1024,986,934,928,895,875,879};
          int y[] = {281,318,327,392,393,371,357,358,334,281,252,252,270,267,277,297};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Kazakhstan";
        country = new Country(countryBoundry, countryName, 937,257);
        countries.add(country);
        
        // Turkey
        { int x[] = {750,837,866,865,812,809,801,751,742,751,768,794};
          int y[] = {367,371,399,413,412,421,422,415,389,366,374,368};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Turkey";
        country = new Country(countryBoundry, countryName, 765,374);
        countries.add(country);
        
        // Volga
        { int x[] = {803,869,854,878,871,924,909,859,775,774,763,784,792,788,818,817,805};
          int y[] = {341,360,312,299,275,268,181,205,205,218,232,248,259,279,290,313,337};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Volga";
        country = new Country(countryBoundry, countryName, 811,207);
        countries.add(country);
        
        // Northern Russia
        { int x[] = {762,774,770,800,807,815,823,838,845,851,859,890,904,908,912,912,907,896,885,879,876,875,866,850,833,814,804,784,773,765,760,752,755,750,750,759,755,753,752,758,760,762};
          int y[] = {229,225,209,204,206,206,205,205,204,186,201,189,186,183,177,143,135,131,128,127,127,137,137,137,130,132,137,132,130,130,130,125,145,153,167,171,183,193,202,210,221,230};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern Russia";
        country = new Country(countryBoundry, countryName, 789,132);
        countries.add(country);
        
        // Novada Zendya
        { int x[] = {860,856,911,884,860,838,827,821,831,849};
          int y[] = {121,102,46,42,55,72,87,105,119,124};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Novada Zendya";
        country = new Country(countryBoundry, countryName, 847,67);
        countries.add(country);
        
        // Urd
        { int x[] = {911,911,909,913,915,925,932,965,970,988,994,991,1001,999,979,957,939};
          int y[] = {102,136,172,203,218,263,250,243,249,247,241,229,226,171,156,121,104};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Urd";
        country = new Country(countryBoundry, countryName, 914,117);
        countries.add(country);
        
        // Siberia
        { int x[] = {944,983,1001,1001,987,1025,1045,1067,1066,1039,1077,1066,1082,1060,1050,973};
          int y[] = {105,154,173,226,248,279,264,270,245,232,180,141,125,87,70,80};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Siberia";
        country = new Country(countryBoundry, countryName, 986,84);
        countries.add(country);
        // Yakutta
        { int x[] = {1067,1076,1082,1090,1107,1134,1168,1192,1244,1226,1157,1078,1063,1083,1068,1077};
          int y[] = {144,180,200,201,186,208,206,162,103,90,82,79,90,123,140,180};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Yakutta";
        country = new Country(countryBoundry, countryName, 1096,84);
        countries.add(country);
        
        // Irkatsk
        { int x[] = {1075,1080,1091,1108,1131,1142,1135,1142,1113,1084,1065,1045,1051,1059,1068,1072};
          int y[] = {180,199,200,188,203,224,228,246,264,264,251,235,216,210,214,183};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Irkatsk";
        country = new Country(countryBoundry, countryName, 1075,197);
        countries.add(country);
        
        // Amer
        { int x[] = {1192,1205,1191,1199,1215,1224,1234,1232,1222,1209,1202,1206,1190,1167,1157,1141,1134};
          int y[] = {162,179,189,204,215,214,221,254,275,278,261,239,243,239,222,224,207};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Amer";
        country = new Country(countryBoundry, countryName, 1175,194);
        countries.add(country);
        
        // Kamchakta 
        { int x[] = {1195,1196,1204,1206,1207,1212,1215,1220,1221,1226,1232,1240,1246,1248,1248,1251,1256,1256,1256,1261,1265,1265,1264,1264,1265,1270,1278,1286,1291,1292,1293,1293,1288,1285,1288,1298,1312,1316,1318,1308,1311,1318,1324,1328,1333,1346,1347,1351,1346,1326,1312,1305,1294,1285,1268,1256,1244,1238,1229,1228,1219,1217,1206,1202,1194,1193};
          int y[] = {164,166,175,183,184,185,187,182,176,172,172,172,173,175,177,179,166,158,158,167,175,185,199,205,211,216,223,227,223,212,202,193,176,169,165,155,155,158,144,137,128,133,141,146,148,145,144,133,128,125,113,106,105,103,102,106,107,114,121,126,131,142,142,148,160,163};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Kamchakta";
        country = new Country(countryBoundry, countryName, 1254,97);
        countries.add(country);
        
        // Japan
        { int x[] = {1229,1234,1242,1245,1246,1252,1263,1274,1283,1286,1286,1284,1283,1283,1287,1280,1285,1290,1290,1273,1265,1259,1249,1251,1252,1247,1247,1255,1257,1258,1257,1259,1259,1254,1246,1244,1242,1238,1228,1223,1222,1212,1214,1220,1224,1225};
          int y[] = {374,355,359,358,343,343,343,338,330,321,315,306,295,288,271,260,247,245,239,239,239,235,230,240,243,254,262,266,268,275,285,298,300,309,300,314,322,329,339,344,349,353,358,362,369,378};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Japan";
        country = new Country(countryBoundry, countryName, 1250,245);
        countries.add(country);
        
        // China
        { int x[] = {1112,1150,1162,1167,1169,1177,1177,1175,1179,1196,1204,1205,1205,1201,1195,1184,1183,1188,1205,1213,1221,1224,1223,1217,1212,1206,1204,1205,1208,1206,1202,1202,1206,1207,1205,1197,1187,1177,1174,1168,1161,1155,1153,1139,1141,1143,1136,1136,1139,1151,1158,1163,1159,1154,1155,1147,1142,1133,1112,1108,1099,1092,1083,1081,1080,1077,1074,1074,1076,1076,1067,1066,1066,1065,1071,1076,1086,1091,1100,1107,1108,1105,1105,1108,1108};
          int y[] = {449,446,450,450,460,459,452,446,432,409,397,388,378,370,359,352,340,332,323,335,331,323,317,315,306,303,299,289,283,277,271,262,257,253,243,242,244,239,238,238,234,226,224,225,237,243,255,257,261,262,262,264,270,273,284,297,298,304,304,310,311,311,312,316,324,334,335,344,346,346,352,355,361,371,381,387,382,388,381,398,410,418,434,440,445};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "China";
        country = new Country(countryBoundry, countryName, 1127,373);
        countries.add(country);
        
        // Mongolia
        { int x[] = {1066,1081,1134,1139,1159,1153,1081,1049,1027,1046,1056,1069,1065,1084};
          int y[] = {255,264,250,263,263,289,315,313,280,264,272,271,253,261};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Mongolia";
        country = new Country(countryBoundry, countryName, 1071,259);
        countries.add(country);
        
        // Xiajano
        { int x[] = {1025,1047,1048,1068,1078,1066,1012,996,986,1008,1014,1025};
          int y[] = {280,302,311,310,319,367,362,350,330,316,295,280};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Xiajano";
        country = new Country(countryBoundry, countryName, 1013,309);
        countries.add(country);
        
        // Xijang
        { int x[] = {1014,1062,1070,1101,1105,1087,1081,1066,1013,1014,1063};
          int y[] = {362,367,382,382,423,426,412,420,390,364,367};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Xijang";
        country = new Country(countryBoundry, countryName, 1023,365);
        countries.add(country);
        
        // India
        { int x[] = {976,984,987,996,997,998,1000,1005,1011,1016,1019,1034,1041,1043,1045,1044,1036,1036,1037,1038,1038,1041,1048,1055,1061,1069,1077,1080,1082,1090,1092,1093,1091,1088,1086,1085,1085,1085,1087,1079,1071,1061,1053,1046,1039,1030,1026,1020,1016,1014,1015,1009,1006,999,992,990,991,994,989,987,985,983,981,980,971,971,981,987,978};
          int y[] = {480,497,508,500,498,517,525,538,549,555,557,571,571,571,565,556,552,548,538,526,516,503,492,482,475,469,468,468,468,475,470,463,459,457,450,437,432,432,417,411,413,420,420,410,407,404,397,396,393,390,366,366,366,368,374,381,387,393,405,413,421,426,436,442,444,454,461,467,477};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "India";
        country = new Country(countryBoundry, countryName, 997,432);
        countries.add(country);
        
        // Thailand
        { int x[] = {1104,1110,1150,1156,1164,1161,1160,1169,1176,1178,1178,1175,1171,1167,1158,1149,1139,1139,1135,1135,1132,1133,1140,1144,1145,1152,1149,1147,1135,1131,1129,1126,1120,1123,1117,1115,1109,1109,1101,1099,1096,1096,1090,1092,1091,1087,1085,1086,1089,1098};
          int y[] = {424,448,446,446,451,463,465,477,479,490,493,500,507,509,513,508,505,500,500,508,518,529,533,534,535,543,547,548,545,545,539,529,528,514,506,504,496,492,499,500,491,485,480,470,462,456,443,436,429,420};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Thailand";
        country = new Country(countryBoundry, countryName, 1097,452);
        countries.add(country);
        
        // Philippines
        { int x[] = {1205,1224,1233,1225,1229,1240,1241,1254,1263,1269,1273,1272,1264,1256,1245,1238,1240,1221,1214,1214,1221,1216,1211,1199,1199,1203};
          int y[] = {513,499,514,526,532,528,523,533,531,523,516,502,498,478,465,452,440,446,455,464,472,484,496,505,508,512};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Philippines";
        country = new Country(countryBoundry, countryName, 1214, 465);
        countries.add(country);
        
        // Indonesia
        { int x[] = {1067,1078,1085,1095,1105,1111,1136,1151,1165,1175,1178,1186,1208,1214,1203,1203,1201,1201,1201,1205,1223,1226,1226,1220,1217,1209,1191,1179,1171,1156,1144,1133,1117,1112,1108,1099,1095,1093,1089,1084,1080,1072,1067};
          int y[] = {545,545,553,563,573,577,570,560,543,540,550,574,570,574,579,583,593,600,603,618,615,617,621,625,628,634,635,628,626,624,623,621,616,611,601,598,590,583,577,571,566,556,550};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Indonesia";
        country = new Country(countryBoundry, countryName, 1126, 561);
        countries.add(country);
        
        // New Guinea
        { int x[] = {1241,1258,1265,1274,1284,1292,1301,1314,1324,1330,1335,1339,1346,1348,1354,1352,1342,1341,1348,1352,1350,1338,1325,1321,1316,1308,1307,1298,1290,1282,1276,1273,1265,1257,1250,1249,1243,1243};
          int y[] = {581,581,588,588,584,585,588,594,602,607,609,610,608,603,603,613,617,621,631,638,640,642,642,632,625,626,632,633,633,627,616,610,607,606,603,602,587,580};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "New Guinea";
        country = new Country(countryBoundry, countryName, 1254,571);
        countries.add(country);
        
        // Hawaii
        { int x[] = {75,98,77,67,49,41,27,25,20,32,42,53,65};
          int y[] = {364,334,325,319,315,310,302,302,309,324,332,337,344};

        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Hawaii";
        country = new Country(countryBoundry, countryName, 73,335);
        countries.add(country);
        
        // New Zealand
        { int x[] = {1267,1283,1301,1300,1303,1314,1316,1316,1342,1351,1352,1355,1345,1339,1332,1323,1313,1312,1317,1322,1324,1320,1308,1302,1299,1288,1277,1274,1269,1266};
          int y[] = {802,807,795,784,779,779,775,763,742,732,725,718,715,710,705,696,690,693,704,715,721,733,746,757,763,772,781,785,792,797};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "New Zealand";
        country = new Country(countryBoundry, countryName, 1314, 696);
        countries.add(country);
        
        // Venezuela
        { int x[] = {328,334,334,342,342,355,357,370,367,387,390,396,419,433,417,391,368,336,331,316,320,316,316,285,280,286,276,326};
          int y[] = {519,520,505,504,498,498,503,497,489,480,498,499,488,475,467,462,442,442,436,440,450,452,433,453,468,483,492,513};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Venezuela";
        country = new Country(countryBoundry, countryName, 308,441);
        countries.add(country);
        
        // Bolivia
        { int x[] = {327,326,334,367,372,385,390,398,397,386,383,358,355,327};
          int y[] = {554,593,627,622,607,606,613,600,582,582,567,562,545,554};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Bolivia";
        country = new Country(countryBoundry, countryName, 333,561);
        countries.add(country);
        
        // Argentina
        { int x[] = { 326,334,364,382,393,393,407,415,396,417,404,395,373,374,360,369,363,358,370,392,392,377,355,339,322,315,311,316,320,322};
          int y[] = { 594,626,622,607,612,626,627,646,668,692,699,718,721,732,734,742,769,784,803,810,815,818,813,801,779,746,708,672,629,597};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Argentina";
        country = new Country(countryBoundry, countryName, 328,683);
        countries.add(country);
        
        // Falkland Islands
        { int x[] = {434,398,386,400,430,461,482,477,456};
          int y[] = {743,742,765,790,801,786,765,742,739};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Falkland Islands";
        country = new Country(countryBoundry, countryName, 416,744);
        countries.add(country);
        
        // Southern United States
        { int x[] = {222,223,206,213,216,225,229,233,237,242,254,262,266,272,278,282,288,290,296,296,303,313,318,323,330,330,335,337,340,342,345,347,347,343,342,346,352,357,366,371,372,373,371,367,377,375,343,338,335,329,325,318,309,303,296,293,289,291,264,264,224};
          int y[] = {307,341,342,353,359,352,354,362,370,371,361,354,353,352,352,352,352,352,352,352,352,352,352,352,359,364,373,376,378,379,380,368,362,355,346,339,336,335,329,328,328,317,313,311,303,295,293,296,300,300,295,296,302,305,312,313,312,309,310,307,307};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Southern United States";
        country = new Country(countryBoundry, countryName, 322,305);
        countries.add(country);
        
        // Midwestern United States
        { int x[] = {228,226,219,217,299,293,305,312,316,321,322,325,327,331,331,334,343,345,335,325,316,311,305,299,295,291,289,265,263,228};
          int y[] = {306,281,280,237,244,252,252,251,250,251,253,258,261,280,280,285,283,290,299,296,299,302,304,308,313,315,307,309,305,308};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Midwestern United States";
        country = new Country(countryBoundry, countryName, 238,243);
        countries.add(country);
        
        // Northern United States
        { int x[] = {345,345,361,370,377,378,386,391,397,394,391,396,399,403,409,402,400,395,390,390,386,378,371,363,360,354,347,347};
          int y[] = {282,292,292,294,296,303,295,293,291,288,281,275,274,272,269,259,251,247,248,262,263,263,264,264,270,275,281,281};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern United States";
        country = new Country(countryBoundry, countryName, 381,286);
        countries.add(country);
        
        // Alberta
        { int x[] = {156,248,249,126,155};
          int y[] = {233,235,163,162,236};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Alberta";
        country = new Country(countryBoundry, countryName, 159,175);
        countries.add(country);
        
        // Quebec
        { int x[] = {354,352,376,394,406,437,475,460,425,396,370,363,369,354};
          int y[] = {199,253,264,250,275,256,243,204,164,150,147,162,189,196};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Quebec";
        country = new Country(countryBoundry, countryName, 357,200);
        countries.add(country);
        
        // Nunavut
        { int x[] = {249,301,313,326,336,351,363,356,348,338,336,336,329,328,330,340,341,331,311,303,298,296,287,287,288,282,269,269,263,256,249,241,233,226,226,218,208,205,205,214,223,227,239,234,246,249,251,241,237,236,227,220,216,208,207,217,223,229,236,243,245,247,249};
          int y[] = {162,162,144,144,139,129,100,97,109,115,109,107,101,83,72,59,54,53,51,51,59,64,75,63,57,57,67,67,65,60,55,45,44,43,43,46,59,65,72,73,71,69,67,73,76,83,83,84,90,99,98,91,88,86,101,112,113,116,122,127,128,129,147};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Nunavut";
        country = new Country(countryBoundry, countryName, 252,70);
        countries.add(country);
        
        // Madagascar
        { int x[] = {901,905,897,888,887,887,875,871,874,856,850,849,845,845,852,854,854,854,853,865,876,881,887,894,901};
          int y[] = {696,717,724,738,751,751,765,773,777,781,780,771,763,760,752,748,739,730,724,721,716,708,702,693,690};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Madagascar";
        country = new Country(countryBoundry, countryName, 851,715);
        countries.add(country);
        
        // Buffalo Islands
        { int x[] = {378,393,400,409,421,421,418,418,415,426,429,429,424,420,419,410,401,393,384,383,377,373,361,355,345,335,334,334,339,351,361,373,379,387,390,394,394,389,378,377};
          int y[] = {131,132,138,141,142,142,134,118,118,118,118,113,105,103,93,89,82,75,70,62,60,60,59,58,60,75,79,85,86,88,90,91,92,93,101,105,112,122,123,129};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Buffalo Islands";
        country = new Country(countryBoundry, countryName, 377,76);
        countries.add(country);
        
        // Soverya
        { int x[] = {935,950,988,1036,1042,947};
          int y[] = {31,58,66,52,29,28};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Soverya";
        country = new Country(countryBoundry, countryName, 963, 29);
        countries.add(country);
        
        // Novashelkye
        { int x[] = {1141,1149,1153,1168,1169,1179,1182,1188,1183,1176,1156,1156,1149};
          int y[] = {40,59,64,64,78,75,63,46,32,28,36,36,39};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Novashelkye";
        country = new Country(countryBoundry, countryName, 1147, 29);
        countries.add(country);
        
        Continent.create();
    }
    
    static public ArrayList<Country> getCountryList() {
        return countries;
    }
}
