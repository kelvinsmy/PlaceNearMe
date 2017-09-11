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




$query = "SELECT 
                         name_en
                            ,lat,lng
                 FROM 
                         toilet";
                 
                 

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