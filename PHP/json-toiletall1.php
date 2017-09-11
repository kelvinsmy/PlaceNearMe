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



$query = "SELECT 
                         id, name_en, address_en, 
                         ACOS(0.5*((1.0+COS(" . $lng . 
                         "*PI()/180-lng*PI()/180))*COS(". $lat . "*PI()/180-lat*PI()/180)-
                        (1.0-COS(".$lng."*PI()/180-lng*PI()/180))*COS(".$lat.
                         "*PI()/180+lat*PI()/180)))*6378.388*1000 AS distance 
                 FROM 
                         toilet
                 ORDER BY 
                         distance ASC";

$res = mysql_query($query);

if($res){
         
         $output .= '[';
         while ($r = mysql_fetch_assoc($res)){
                 $output .= '{"id":"'.$r[id].'","name_en":"'.$r[name_en].'","address_en":"'.$r[address_en].'","distance":"'.$r[distance].'"},';
         }
         $output = substr($output,0,-1);
         $output .= ']';
         $output .= '}';
         echo $output;
}
?>