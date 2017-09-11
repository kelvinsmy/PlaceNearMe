<?php

function insert_sql($id,$district,$name_en,$address_en,$lat,$lng) {
        $insert = "INSERT INTO `toilet` (`id`,`district`,`name_en`,`name_hk`,`name_cn`,`address_en`,`address_hk`,`address_cn`,`lat`,`lng`) VALUES ";
        $insert .= "( {$id},'{$district}',";
        $insert .= "'{$name_en}','','',";
        $insert .= "'{$address_en}','','',";
        $insert .= "{$lat},{$lng} );";
        $res = mysql_query($insert);
        if($res) {
                echo '<p style="color:blue;">Toilet Inserted Successfully</p>';
        } else {
                echo '<p style="color:red;">Toilet Insertion Failed!</p>';
        }
}

function update_sql_hk($id,$name_hk,$address_hk) {
        $query_u = "UPDATE `toilet` SET `name_hk`='{$name_hk}', `address_hk`='{$address_hk}' WHERE `id`='{$id}';";
        $res_u = mysql_query($query_u);
        if($res_u) {
                echo '<p style="color:blue;">Toilet Updated Successfully</p>';
        } else {
                echo '<p style="color:red;">Toilet Update Failed!</p>';
        }
}

function update_sql_cn($id,$name_cn,$address_cn) {
        $query_u = "UPDATE `toilet` SET `name_cn`='{$name_cn}', `address_cn`='{$address_cn}' WHERE `id`='{$id}';";
        $res_u = mysql_query($query_u);
        if($res_u) {
                echo '<p style="color:blue;">Toilet Updated Successfully</p>';
        } else {
                echo '<p style="color:red;">Toilet Update Failed!</p>';
        }
}

$gaSql['user'] = "root";
$gaSql['password'] = "";
$gaSql['db'] = "test";
$gaSql['server'] = "localhost";

$gaSql['link'] =  mysql_pconnect( $gaSql['server'], $gaSql['user'], $gaSql['password']  ) or
  die( 'Could not open connection to server' );

mysql_select_db( $gaSql['db'], $gaSql['link'] ) or 
  die( 'Could not select database '. $gaSql['db'] );

mysql_query('SET NAMES utf8');
mysql_query('SET CHARACTER_SET_CLIENT=utf8');
mysql_query('SET CHARACTER_SET_RESULTS=utf8');

//Get English version data first and insert new records
$url = "http://www.fehd.gov.hk/english/map/fehd_map_e.xml";
$xml = simplexml_load_file($url,'SimpleXMLElement',LIBXML_NOCDATA);
foreach($xml->fehd_service_locations->map as $single) {
        $id = $single->mapID;
        $district = $single->districtID;
        $cat = $single->map_type;
        $name_en = $single->name_e;
        $address_en = $single->address_e;
        $latlng = $single->google_coordinate;
        list($lat,$lng) =explode(',',$latlng);
        if($cat=='toilet') {
                insert_sql($id,$district,$name_en,$address_en,$lat,$lng);
        }
}

//Get Traditional Chinese version data and update records
$url = "http://www.fehd.gov.hk/tc_chi/map/fehd_map_c.xml";
$xml = simplexml_load_file($url,'SimpleXMLElement',LIBXML_NOCDATA);
foreach($xml->fehd_service_locations->map as $single) {
        $id = $single->mapID;
        $cat = $single->map_type;
        $name_hk = $single->name_c;
        $address_hk = $single->address_c;
        if($cat=='toilet') {
                update_sql_hk($id,$name_hk,$address_hk);
        }
}

//Get Simplified Chinese version data and update records
$url = "http://www.fehd.gov.hk/sc_chi/map/fehd_map_s.xml";
$xml = simplexml_load_file($url,'SimpleXMLElement',LIBXML_NOCDATA);
foreach($xml->fehd_service_locations->map as $single) {
        $id = $single->mapID;
        $cat = $single->map_type;
        $name_cn = $single->name_s;
        $address_cn = $single->address_s;
        if($cat=='toilet') {
                update_sql_cn($id,$name_cn,$address_cn);
        }
}

mysql_close($gaSql['link']);

?>