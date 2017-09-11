<?php

$dbHost = "localhost";
$dbUser = "root";
$dbPass = "";
$dbName = "test";

$conn = mysql_connect($dbHost, $dbUser, $dbPass) or die ("");
mysql_select_db($dbName);
mysql_query("SET NAMES utf8");
mysql_query("SET CHARACTER_SET_CLIENT=utf8");
mysql_query("SET CHARACTER_SET_RESULTS=utf8");

//DEAFULT LOCATION
$lat = "22.305648";
$lng = "114.171259";

if(isset($_GET['row_index']) && is_numeric($_GET['row_index'])){
         $rowIndex = $_GET['row_index'];
}else{
         $rowIndex = 0;
}

if(isset($_GET['lat']) && isset($_GET['lng'])){
         $lat = mysql_real_escape_string($_GET['lat']);
         $lng = mysql_real_escape_string($_GET['lng']);
}


$query = "SELECT 
                         name_en,
                         ACOS(0.5*((1.0+COS(" . $lng . 
                         "*PI()/180-lng*PI()/180))*COS(". $lat . "*PI()/180-lat*PI()/180)-
                        (1.0-COS(".$lng."*PI()/180-lng*PI()/180))*COS(".$lat.
                         "*PI()/180+lat*PI()/180)))*6378.388*1000 AS distance ,lat,lng
                 FROM 
                         toilet
                 ORDER BY 
                         distance ASC
                 LIMIT " . $rowIndex . ",10";
                 

$res = mysql_query($query);

if($res){
         $output .= '[';
         while ($r = mysql_fetch_assoc($res)){
                 $output .= '{"name_en":"'.$r[name_en].'","lat":"'.$r[lat].'","lng":"'.$r[lng].'"},';
         }
         $output = substr($output,0,-1);
         $output .= ']';
         
         echo $output;
}
?>