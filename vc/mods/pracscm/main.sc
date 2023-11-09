// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// **********************************Vice City Main Script**********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
  
SCRIPT_NAME Main

// ***************************************SETUP*********************************************
// ******************************************************************************************

DO_FADE 0 FADE_OUT
SET_TOTAL_NUMBER_OF_MISSIONS 0
SET_PROGRESS_TOTAL 0
SET_MAX_WANTED_LEVEL 4				    
SET_COLLECTABLE1_TOTAL 0							 
SET_DEATHARREST_STATE OFF
SET_TIME_OF_DAY 8 00 // Practice SCM - Set time to 8am

// *****************************************CREATE PLAYER************************************   

VAR_INT player1 scplayer

REQUEST_COLLISION -1164.690063 -1308.872559 // Practice SCM - Parking lot at Viceport
LOAD_SCENE -1164.690063 -1308.872559 14.5 // Practice SCM - Parking lot at Viceport
CREATE_PLAYER 0 -1164.690063 -1308.872559 14.5 player1 // Practice SCM - Spawn at the parking lot at Viceport


GET_PLAYER_CHAR player1 scplayer 							   

LOAD_AND_LAUNCH_MISSION initial.sc
//LOAD_AND_LAUNCH_MISSION_EXCLUSIVE initial.sc

VAR_INT stadium_door_1
CREATE_OBJECT_NO_OFFSET dtn_staddoora -1109.615 1330.097 20.372 stadium_door_1
DONT_REMOVE_OBJECT stadium_door_1

VAR_INT stadium_door_2
CREATE_OBJECT_NO_OFFSET dtn_staddoorb -1109.615 1331.932 20.372 stadium_door_2
DONT_REMOVE_OBJECT stadium_door_2

VAR_INT stadium_poster
CREATE_OBJECT_NO_OFFSET dthotring_a -1037.08 1340.258 36.552 stadium_poster
DONT_REMOVE_OBJECT stadium_poster

VAR_INT day_flag day_counter hours_s minutes_s flag_stadium_doors

// *************************************VARIABLES********************************************

VAR_INT gate_slide_loop skip_flag mansion_blip remove_hycobuy_pickup remove_ochebuy_pickup view_of_ocean_view
VAR_INT controlmode lawyer_blip1 mansion_save_pickup remove_mansion_pickup remove_lnkvbuy_pickup
VAR_INT mission_trigger_wait_time switch_off_starfish_gates	car_help_played	buddycar remove_taxiwar_save_pickup
VAR_INT breakout_timer breakout_timer_start breakout_diff played_reminder_help remove_washbuy_pickup
VAR_INT audio_is_loading audio_has_loaded print_help_for_jacking remove_nbmnbuy_pickup remove_strpbuy_pickup
VAR_INT first_two_samples second_two_samples third_two_samples forth_two_samples fifth_two_samples sixth_two_samples
VAR_INT print_save_game_help print_first_help print_help_for_radar drive_by_help bike_help remove_boatbuy_pickup remove_icecream_pickup
VAR_INT import_car_generator1_created import_car_generator2_created import_car_generator3_created import_car_generator4_created
VAR_INT flag_kickstart_passed_1stime buddy_blip_active buddy_blip skip_timer1_acive	colar_cuffs_blip remove_skumbuy_pickup
VAR_INT button_pressed serg_mission3_start baron_blip1 fastest_boat fastest_boat_blip goto_hotel_contact_blip
VAR_INT remove_carshow_pickup carshow_save_pickup remove_vcptbuy_pickup taxiwar_save_pickup
VAR_INT remove_printers_pickup remove_porn_pickup remove_malibu_pickup printers_pickup porn_pickup malibu_pickup
VAR_INT star_gun3 star_gun4	star_health3 star_arm1 cokerun_trigger stop_in_marker
VAR_INT buddys_lambo police_chopper	Storm_Warning camera_help_played
VAR_INT deluxo_prize_car_gen sabretur_prize_car_gen	sandking_prize_car_gen Hotring_prize_car_gen created_final_shirt
 
VAR_FLOAT one_sixteenth one_thirtysecond one_sixtyfourth   
VAR_FLOAT player_x player_y player_z ground_z
VAR_FLOAT clothes_shopX clothes_shopY clothes_shopZ game_percentage

VAR_INT wasted_help wanted_help  police_bribe_help wanted_star_help  // flag names

VAR_INT flag_played_rock3_before // Used so player can skip the first part of the mission if they have played it once.

VAR_INT player_had_reload_award // Used to give the player the fast reload award once he has got a score over 50 in the range oddjob

VAR_INT flag_played_bank2_before // Used for the player to be able to skip the text bit

remove_carshow_pickup = 0

//POLICE BRIBES********************************************************************************

VAR_INT beach_bribe1 beach_bribe2 beach_bribe3 beach_bribe4 beach_bribe5 beach_bribe6 porn_bribe1 
VAR_INT main_bribe1 main_bribe2 main_bribe3 main_bribe4 main_bribe5 main_bribe6

//BEACH
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 393.9 -60.2 11.5 beach_bribe1 //Not far from Construction Site behind some houses
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 116.0 -1313.1 4.4 beach_bribe2 //Through Underground Shopping mall (washinton)
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 393.7 -660.6 10.7 beach_bribe3 //Middle of alleyways at back of Ocean Drive
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 470.7 70.1 10.8 beach_bribe4 //Just down from Pizza Hut in Vice Point
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 382.7 364.1 10.8 beach_bribe5 //In Alleyway in centre of Vice Point
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 422.4 971.2 12.1 beach_bribe6 //Near garages at Big Mall
 
//PORN ISLAND
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 89.1 887.4 10.5 porn_bribe1 //Down Unused street on Prawn Island
    
//GOLF ISLAND

//STAR ISLAND

//MAINLAND
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -822.7 1304.5 11.7 main_bribe1 //Between shortcut in downtown
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -900.69 251.4 17.1 main_bribe2 //over jump at top of Little Hiati into Move Over Miami Sign
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -973.7 61.0 10.4 main_bribe3 //in little haiti close by auntie poulets hut
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -937.9 -114.1 17.0 main_bribe4 //Over little aquaduct in little hiati
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -1015.9 -627.9 11.2 main_bribe5 //Through alleyway in little havana
CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW -906.3 -834.0 15.7 main_bribe6 //Over jump from main drag into car yard

// ***************************************CAR GENERATORS***************************************

CREATE_CAR_GENERATOR -1022.6 -868.6 12.2 175.0 deluxo -1 -1 TRUE 0 0 0 10000 deluxo_prize_car_gen
SWITCH_CAR_GENERATOR deluxo_prize_car_gen 0

CREATE_CAR_GENERATOR -1014.1 -869.4 12.2 188.0 sabretur -1 -1 TRUE 0 0 0 10000 sabretur_prize_car_gen
SWITCH_CAR_GENERATOR sabretur_prize_car_gen 0

CREATE_CAR_GENERATOR -1014.3 -868.8 17.9 195.0 sandking -1 -1 TRUE 0 0 0 10000 sandking_prize_car_gen
SWITCH_CAR_GENERATOR sandking_prize_car_gen 0

CREATE_CAR_GENERATOR -1023.2 -868.1 17.9 172.0 Hotring -1 -1 TRUE 0 0 0 10000 Hotring_prize_car_gen
SWITCH_CAR_GENERATOR Hotring_prize_car_gen 0

CREATE_CAR_GENERATOR -379.0 -632.1 10.2 187.2 seaspar -1 -1 0 0 0 0 10000 gen_car148 //behind mansion
SWITCH_CAR_GENERATOR gen_car148 0

CREATE_CAR_GENERATOR -1720.3 -239.6 14.8 92.0 rhino -1 -1 0 0 0 0 10000 gen_car149 //at army barracks
SWITCH_CAR_GENERATOR gen_car149 0

CREATE_CAR_GENERATOR -1681.2 -103.7 14.7 180.0 hunter -1 -1 0 0 0 0 10000 gen_car150 //at army barracks
SWITCH_CAR_GENERATOR gen_car150 0

CREATE_CAR_GENERATOR -72.4 -1607.9 12.7 0.0 hunter -1 -1 0 0 0 0 10000 south_beach_hunter //at south beach
SWITCH_CAR_GENERATOR south_beach_hunter 0

// *************************************LOCATE BLOB VARIABLE STUFF****************************

VAR_INT blob_flag

// zero = false no blob displayed
// one = true blob is displayed					 
// before the loop set this flag tSo the way you want it displayed or nothing will happen

// *****************************************SPECIAL CHARACTERS********************************

// Cutscene stuff
VAR_INT cs_time  // timer for cutscenes
VAR_INT cs_player
VAR_INT cs_mobtable
VAR_INT cs_sfrenda // sonny intro
VAR_INT cs_sfrendb // sonny intro
VAR_INT cs_sgoona // Sonny intro
VAR_INT cs_sgoonb // Sonny intro
VAR_INT cs_case1
VAR_INT cs_case2
VAR_INT cs_gun1
VAR_INT cs_gun2
VAR_INT buddy
VAR_INT cs_buddy
VAR_INT cs_phil
VAR_INT cs_sonny 
VAR_INT cs_phnplay
VAR_INT cs_phoncol
VAR_INT cs_ken
VAR_INT cs_kent 
VAR_INT cs_dealer 
VAR_INT cs_mgoona 
VAR_INT cs_assas1 //assain1 in the intro
VAR_INT cs_assas2 //assain2 in the intro
VAR_INT cs_assas3 //assain3 in the intro
VAR_INT cs_chopper //Helicoter in intro
VAR_INT cs_avery
VAR_INT	cs_colonel
VAR_INT cs_svntray
VAR_INT cs_drinka
VAR_INT cs_drinkb
VAR_INT cs_drinkc
VAR_INT cs_bigm  // biker leader
VAR_INT	cs_mserver //Barman
VAR_INT	cs_server //Barman
VAR_INT cs_bmchcue // Biker leader pool cue 
VAR_INT cs_cougcue // Cougar pool cue
VAR_INT cs_poolballs // Pool balls
VAR_INT cs_diaz
VAR_INT cs_merc
VAR_INT cs_direc
VAR_INT cs_mporna
VAR_INT cs_candy
VAR_INT cs_gonz
VAR_INT cs_kelly // counterfeiting
VAR_INT cs_cougr // biker
VAR_INT cs_zepp // biker
VAR_INT cs_apoulet  // haitian
VAR_INT cs_dick // rockband
VAR_INT cs_jezz // rockband
VAR_INT cs_percy // rockband 
VAR_INT cs_floozya // Used in counterfeiting
VAR_INT cs_floozyb // Used in laywer 2
VAR_INT cs_floozyc
VAR_INT cs_bj
VAR_INT cs_pablo 
VAR_INT cs_papa
VAR_INT cs_pepe
VAR_INT cs_umbto
VAR_INT cs_shootra // Shooting range guys
VAR_INT cs_shootrb // Shooting range guys
VAR_INT cs_philgun1 // Phils gun bank2b
VAR_INT cs_philgun2 // Phils gun bank2b
VAR_INT cs_hilary // Hilary the getaway driver
VAR_INT cs_hilcar // Hilarys car
VAR_INT cs_cam
VAR_INT cs_clubfan // Fan in the back room in the club
VAR_INT cs_clubchair // Chairs in the back room in the club
VAR_INT cs_lwchara
VAR_INT cs_lwcharb	
VAR_INT cs_dgoona 
VAR_INT cs_dgoonc 
VAR_INT cs_crewa
VAR_INT cs_crewb
VAR_INT cs_cmraman 
VAR_INT cs_lobtray  
VAR_INT cs_lobster 
VAR_INT cs_coknife  
VAR_INT cs_coltray
VAR_INT cs_lcfan
VAR_INT cs_dcfan
VAR_INT cs_espreso
VAR_INT cs_invite
VAR_INT cs_deagl
VAR_INT cs_introcar
VAR_INT cs_plane
VAR_INT cs_camera
VAR_INT cs_lawdoor
VAR_INT cs_phone
VAR_INT cs_rifle
VAR_INT cs_kettle
VAR_INT cs_htable
VAR_INT cs_drchair
VAR_INT cs_schair
VAR_INT cc_fan
VAR_INT cc_fan1
VAR_INT	cs_dlove

// **************************************Game variables**************************************

VAR_INT flag_intro_passed 

//Lawyer missions
VAR_INT hotel_contact_blip 
VAR_INT flag_hotel_mission1_passed
VAR_INT lawyer_contact_blip 
VAR_INT flag_lawyer_mission1_passed
VAR_INT flag_lawyer_mission2_passed
VAR_INT flag_lawyer_mission3_passed
VAR_INT flag_lawyer_mission4_passed

//Generals missions
VAR_INT general_contact_blip
VAR_INT flag_general_mission1_passed
VAR_INT flag_general_mission2_passed
VAR_INT flag_general_mission3_passed
VAR_INT flag_general_mission4_passed
VAR_INT flag_general_mission5_passed

//Barons missions
VAR_INT baron_contact_blip
VAR_INT flag_baron_mission1_passed
VAR_INT flag_baron_mission2_passed
VAR_INT flag_baron_mission3_passed
VAR_INT flag_baron_mission4_passed
VAR_INT flag_baron_mission5_passed
VAR_INT start_baron1_script_flag

//Kent missions
VAR_INT kent_contact_blip
VAR_INT flag_kent_mission1_passed

//Sergio missions
VAR_INT sergio_contact_blip
VAR_INT flag_sergio_mission1_passed
VAR_INT flag_sergio_mission2_passed
VAR_INT flag_sergio_mission3_passed

//bankjob missions
VAR_INT bankjob_contact_blip
VAR_INT flag_bankjob_mission1_passed
VAR_INT flag_bankjob_mission2_passed
VAR_INT flag_bankjob_mission3_passed
VAR_INT flag_bankjob_mission4_passed
VAR_INT flag_player_on_bank_mission

//phil missions
VAR_INT phil_contact_blip
VAR_INT flag_phil_mission1_passed
VAR_INT flag_phil_mission2_passed

//porn missions
VAR_INT porn_contact_blip
VAR_INT flag_porn_mission1_passed
VAR_INT flag_porn_mission2_passed
VAR_INT flag_porn_mission3_passed
VAR_INT flag_porn_mission4_passed

//exterior gates - this needs to go here for the gates opening to work
VAR_INT porn_north_gate_open
VAR_INT porn_south_gate_open
VAR_INT porn_north_gate_closed
VAR_INT porn_south_gate_closed

//north gate
CREATE_OBJECT_NO_OFFSET ci_gatesclosed 11.697 963.176 12.258 porn_north_gate_closed
DONT_REMOVE_OBJECT porn_north_gate_closed

//south gate
CREATE_OBJECT_NO_OFFSET ci_backgateclose -11.853 884.06 13.542 porn_south_gate_closed
DONT_REMOVE_OBJECT porn_south_gate_closed

//protect missions
VAR_INT protect_contact_blip
VAR_INT flag_protect_mission1_passed
VAR_INT flag_protect_mission2_passed
VAR_INT flag_protect_mission3_passed
VAR_INT flag_finale_mission1_passed
VAR_INT flag_finale_mission2_passed

//counter missions
VAR_INT counter_contact_blip
VAR_INT flag_counter_mission1_passed
VAR_INT flag_counter_mission2_passed

//Bikers missions
VAR_INT bikers_contact_blip
VAR_INT flag_bikers_mission1_passed
VAR_INT flag_bikers_mission2_passed
VAR_INT flag_bikers_mission3_passed

//Cuban missions
VAR_INT cuban_contact_blip
VAR_INT flag_cuban_mission1_passed
VAR_INT flag_cuban_mission2_passed
VAR_INT flag_cuban_mission3_passed
VAR_INT flag_cuban_mission4_passed

//Haitian missions
VAR_INT haitian_contact_blip
VAR_INT flag_haitian_mission1_passed
VAR_INT flag_haitian_mission2_passed
VAR_INT flag_haitian_mission3_passed

//rock missions
VAR_INT rock_contact_blip
VAR_INT flag_rock_mission1_passed
VAR_INT flag_rock_mission2_passed
VAR_INT flag_rock_mission3_passed
VAR_INT flag_rock_mission4_passed
//VAR_INT flag_rock_mission5_passed

//Assasin missions
VAR_INT assin_phone1
VAR_INT assin_phone2
VAR_INT assin_phone3
VAR_INT assin_phone4
VAR_INT assin_phone5 
VAR_INT assasin_contact_blip
VAR_INT flag_assin_mission1_passed
VAR_INT flag_assin_mission2_passed
VAR_INT flag_assin_mission3_passed
VAR_INT flag_assin_mission4_passed
VAR_INT flag_assin_mission5_passed
VAR_INT counter_number_of_car_assin_done
VAR_INT counter_number_of_sniper_assin_done
VAR_INT counter_number_of_bat_assin_done

//assassin payphone locations
GRAB_PHONE 36.90384674 -1023.300232 assin_phone1
GRAB_PHONE 482.453 244.221 assin_phone2
GRAB_PHONE 38.3409996 -1023.300232 assin_phone3
GRAB_PHONE -1482.801147 -825.258728 assin_phone4
GRAB_PHONE -976.7705 -530.5467 assin_phone5

//Taxi War missions
VAR_INT taxiwar_contact_blip
VAR_INT flag_taxicut_mission1_passed
VAR_INT flag_taxiwar_mission1_passed
VAR_INT flag_taxiwar_mission2_passed
VAR_INT flag_taxiwar_mission3_passed
VAR_INT players_cab created_players_cab  

// Global variables for missions
VAR_INT flag_player_on_mission
VAR_INT flag_player_on_oddjob
VAR_INT ambulance_pager_flag total_saved_peds total_fires_exting
 
DECLARE_MISSION_FLAG flag_player_on_mission

// Odd job variables
VAR_INT	been_in_ambulance_before, flag_player_on_ambulance_mission // Ambulance Missions
VAR_INT	been_in_a_firetruk_before, flag_player_on_fire_mission // Fire Truck Missions
VAR_INT	been_in_a_copcar_before// Cop Car Missions
VAR_INT	been_in_a_gang_car1_before, flag_player_on_gang_car1_mission // Gang Car Mission1 Mafia car
VAR_INT	been_in_a_gang_car2_before, flag_player_on_gang_car2_mission // Gang Car Mission2 Diablo car
VAR_INT	been_in_a_gang_car3_before, flag_player_on_gang_car3_mission // Gang Car Mission3 Yakuza car
VAR_INT	been_in_a_gang_car4_before, flag_player_on_gang_car4_mission // Gang Car Mission4 Yardie car
VAR_INT passed_usj_counter // Unique Stunt Jump Counter
VAR_INT got_siren_help_before
VAR_INT been_in_a_garbage_truck_before, flag_player_on_garbage_mission // Garbage Truck mission
VAR_INT	flag_just_done_carbomb_mission
VAR_INT	flag_just_done_speed_mission
VAR_INT flag_icecream_mission1_passed
VAR_INT flag_icecut_mission1_passed

// 4x4/carpark Missions
VAR_INT	flag_4x4_mission1_passed flag_4x4_mission2_passed flag_4x4_mission3_passed
VAR_INT flag_4x4one_trigger flag_4x4two_trigger flag_4x4three_trigger
VAR_INT record_4x4_one record_temp
VAR_INT flag_intro1_before flag_intro2_before flag_intro3_before flag_intro_carpark1_before
VAR_INT flag_carpark1_passed record_carpark1 flag_carpark1_trigger
VAR_INT flag_launch_4x4_1_ok flag_usj_off
VAR_INT bmx_1_trigger
VAR_INT flag_intro  timer_intro_start intro_time_lapsed	timer_intro_now

// Kickstart
VAR_INT	flag_kickstart_mission1_passed
VAR_INT kick_trigger

// *******************************************BMX variables*****************************************************

VAR_INT flag_bmx_1_passed flag_bmx_2_passed flag_bmx_3_passed
VAR_FLOAT bmx_1_x bmx_1_y bmx_1_z

// Taxi mission
VAR_INT taxi_passed, been_in_a_taxi_before, flag_taxi1_mission_launched, been_in_taxi1_before, new_taxi_created_before 
VAR_INT spray_taxi done_taxi_help

// RC Demolition
VAR_INT rec_rc1 rec_rc2 rec_rc3 rec_rc4 rec_rc5 rec_rc6	  
VAR_INT flag_just_done_rc_mission flag_rc1_passed flag_rc2_passed flag_rc3_passed flag_rc4_passed

//HOOKER MISSION
VAR_INT flag_mission_hooker1_passed
VAR_INT flag_hooker_mission1_launched

//PIZZA MISSION
VAR_INT flag_pizza_mission_passed 
VAR_INT flag_pizza_trigger

//MM MISSION
VAR_INT flag_mm_mission_passed 
VAR_INT flag_mm_trigger

//MOBILE PHONE
VAR_INT flag_first_asset_complete
VAR_INT timer_mobile_start timer_mobile_now timer_mobile_diff

//RC HELI MISSION
VAR_INT has_player_just_done_rcheli_mission
VAR_INT flag_complete_rcheli_once

//RC BUGGY CHECKPOINT RACE
VAR_INT has_player_just_done_rcrace_mission

//RC PLANE RACE
VAR_INT has_player_just_done_rcplane_mission

// *******************************************RACES variables*****************************************************

VAR_INT	race1_best_position
VAR_INT	race1_best_time
VAR_INT	race2_best_position
VAR_INT	race2_best_time
VAR_INT	race3_best_position
VAR_INT	race3_best_time
VAR_INT	race4_best_position
VAR_INT	race4_best_time
VAR_INT	race5_best_position
VAR_INT	race5_best_time
VAR_INT	race6_best_position
VAR_INT	race6_best_time
VAR_INT heli1_checkpoint_best_time
VAR_INT heli2_checkpoint_best_time
VAR_INT heli3_checkpoint_best_time
VAR_INT heli4_checkpoint_best_time

// ***************************************BLIPS******************************************************


VAR_INT the_lawyer_blip the_kent_blip the_general_blip the_baron_blip the_sergio_blip 
VAR_INT the_bankjob_blip the_phil_blip the_porn_blip the_protection_blip the_counter_blip
VAR_INT the_biker_blip the_rock_blip the_cuban_blip the_haitian_blip the_assasin_blip the_taxiwar_blip
VAR_INT weapon_shop1_blip weapon_shop2_blip weapon_shop3_blip hardware_shop1 hardware_shop2 hardware_shop3
VAR_INT spray_shop1 spray_shop2 spray_shop3 spray_shop5	hotel_save_pickup remove_hotel_pickup

VAR_FLOAT x y z the_yacht_x the_yacht_y the_yacht_z
VAR_INT car
VAR_INT got_money_help_print

VAR_FLOAT hotelX hotelY hotelZ
VAR_FLOAT lawyerX lawyerY lawyerZ
VAR_FLOAT generalX generalY generalZ
VAR_FLOAT baronX baronY baronZ
VAR_FLOAT sergioX sergioY sergioZ
VAR_FLOAT bankjobX bankjobY bankjobZ
VAR_FLOAT pornX pornY pornZ
VAR_FLOAT counterX counterY counterZ
VAR_FLOAT bikersX bikersY bikersZ
VAR_FLOAT cubanX cubanY cubanZ
VAR_FLOAT haitianX haitianY haitianZ
VAR_FLOAT rockX rockY rockZ
VAR_FLOAT taxiwarX taxiwarY taxiwarZ 
VAR_FLOAT philX philY philZ
VAR_FLOAT icecreamX icecreamY icecreamZ 
VAR_FLOAT heli1X heli1Y heli1Z 
VAR_FLOAT heli2X heli2Y heli2Z 
VAR_FLOAT heli3X heli3Y heli3Z 
VAR_FLOAT heli4X heli4Y heli4Z 
VAR_FLOAT baron2X baron2Y baron2Z
VAR_FLOAT kentX kentY kentZ

VAR_FLOAT ass_1_x ass_1_Y ass_1_Z
VAR_FLOAT ass_2_x ass_2_Y ass_2_Z
VAR_FLOAT ass_3_x ass_3_Y ass_3_Z
VAR_FLOAT ass_4_x ass_4_Y ass_4_Z
VAR_FLOAT ass_5_x ass_5_Y ass_5_Z

VAR_FLOAT ammu1X ammu1Y ammu1Z 
VAR_FLOAT ammu2X ammu2Y ammu2Z 
VAR_FLOAT ammu3X ammu3Y ammu3Z
VAR_FLOAT hard1X hard1Y hard1Z 
VAR_FLOAT hard2X hard2Y hard2Z 
VAR_FLOAT hard3X hard3Y hard3Z

VAR_FLOAT printbuyX printbuyY printbuyZ
VAR_FLOAT carbuyX carbuyY carbuyZ
VAR_FLOAT pornbuyX pornbuyY pornbuyZ
VAR_FLOAT icebuyX icebuyY icebuyZ
VAR_FLOAT taxibuyX taxibuyY taxibuyZ
VAR_FLOAT bankbuyX bankbuyY bankbuyZ
VAR_FLOAT boatbuyX boatbuyY boatbuyZ
VAR_FLOAT strpbuyX strpbuyY strpbuyZ
VAR_FLOAT nbmnbuyX nbmnbuyY nbmnbuyZ

VAR_FLOAT porncashX porncashY porncashZ
VAR_FLOAT boatcashX boatcashY boatcashZ
VAR_FLOAT taxicashX taxicashY taxicashZ
VAR_FLOAT lnkvbuyX lnkvbuyY lnkvbuyZ
VAR_FLOAT hycobuyX hycobuyY hycobuyZ
VAR_FLOAT ochebuyX ochebuyY ochebuyZ
VAR_FLOAT washbuyX washbuyY washbuyZ
VAR_FLOAT vcptbuyX vcptbuyY vcptbuyZ
VAR_FLOAT skumbuyX skumbuyY skumbuyZ

lnkvbuyX = 304.5
lnkvbuyY = 376.3 
lnkvbuyZ = 12.7

hycobuyX = -834.8  
hycobuyY = 1306.9
hycobuyZ = 11.0

ochebuyX = 14.0 
ochebuyY = -1500.7 
ochebuyZ = 12.7

washbuyX = 88.5 
washbuyY = -804.7 
washbuyZ = 11.2

vcptbuyX = 531.4 
vcptbuyY = 1273.7 
vcptbuyZ = 17.6

skumbuyX = -560.1 
skumbuyY = 703.6 
skumbuyZ = 20.5

hotelX = 209.5
hotelY = -1288.9
hotelZ = 12.4

sergioX = 257.1 //190.5
sergioY = -231.7 //-366.2
sergioZ	= 10.0//10.0

printbuyX =	-1059.6
printbuyY =	-274.5
printbuyZ =	11.4

carbuyX = -1007.3
carbuyY = -869.9
carbuyZ = 12.8

pornbuyX = 15.2
pornbuyY = 962.6
pornbuyZ = 10.9

icebuyX = -864.3
icebuyY = -576.6
icebuyZ = 11.0

taxibuyX = -1011.7
taxibuyY = 203.9
taxibuyZ = 11.2

bankbuyX = 487.2  
bankbuyY = -81.5
bankbuyZ = 11.4

boatbuyX = -685.8
boatbuyY = -1495.6
boatbuyZ = 12.5

taxiwarX = -1003.1
taxiwarY = 207.0
taxiwarz = 10.0

generalX = -246.6 
generalY = -1360.5 
generalZ = 7.3 

baronX = -378.5
baronY = -551.3
baronZ = 18.2

bankjobX = 463.9  // 496.3
bankjobY = -58.5 // -84.0
bankjobZ = 10.5 // 9.4

philX = -1101.1
philY = 343.2
philZ = 10.2

pornX = -69.4
pornY = 932.7
pornZ = 9.9

counterX = -1062.6
counterY = -278.8
counterZ = 11.0

lawyerX = 119.2
lawyerY = -826.9
lawyerZ = 9.7		  
				 
kentX = 488.6 // 491.0 
kentY = -75.4 // -77.7
kentZ = 10.4 // 10.4

bikersX = -597.3
bikersY = 652.9
bikersZ	= 10.0

rockX = -875.5
rockY = 1159.3
rockZ = 10.2

cubanX = -1170.0  
cubanY = -606.9
cubanZ = 10.6

haitianX = -962.4	  
haitianY = 143.0
haitianZ = 8.2

strpbuyX = 99.5
strpbuyY = -1468.5
strpbuyZ = 9.9

nbmnbuyX = 428.4
nbmnbuyY = 605.9
nbmnbuyZ = 12.2

icecreamX =	-865.9  
icecreamY =	-571.9 
icecreamZ =	11.0

porncashX =	-1.9 
porncashY =	959.9 
porncashZ =	10.9

boatcashX =	-640.8 
boatcashY =	-1491.8 
boatcashZ =	13.7

taxicashX =	-997.1 
taxicashY =	189.8 
taxicashZ =	11.4

the_sergio_blip = RADAR_SPRITE_AVERY
the_taxiwar_blip = RADAR_SPRITE_KCABS
the_lawyer_blip = RADAR_SPRITE_LAWYER
the_kent_blip = RADAR_SPRITE_KENT //RADAR_SPRITE_CLUB
the_general_blip = RADAR_SPRITE_CORTEZ 
the_baron_blip = RADAR_SPRITE_DIAZ
the_bankjob_blip = RADAR_SPRITE_MALIBUCLUB
the_phil_blip = RADAR_SPRITE_PHIL
the_porn_blip = RADAR_SPRITE_FILM
the_protection_blip = RADAR_SPRITE_TOMMY
the_counter_blip = RADAR_SPRITE_PRINTWORKS

the_biker_blip = RADAR_SPRITE_BIKERS
the_rock_blip = RADAR_SPRITE_LOVEFIST
the_cuban_blip = RADAR_SPRITE_CUBANS
the_haitian_blip = RADAR_SPRITE_HAITIANS
the_assasin_blip = RADAR_SPRITE_PHONE


ADD_SPRITE_BLIP_FOR_CONTACT_POINT lawyerX lawyerY lawyerZ the_lawyer_blip lawyer_contact_blip
	REMOVE_BLIP	lawyer_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT 491.0 -77.7 10.4 the_kent_blip kent_contact_blip 
	REMOVE_BLIP kent_contact_blip

// GANG BLIPS
ADD_SPRITE_BLIP_FOR_CONTACT_POINT bikersX bikersY bikersZ the_biker_blip bikers_contact_blip
	REMOVE_BLIP bikers_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT rockX rockY rockZ the_rock_blip rock_contact_blip
	REMOVE_BLIP rock_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT cubanX cubanY cubanZ the_cuban_blip cuban_contact_blip
	REMOVE_BLIP cuban_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT haitianX haitianY haitianZ the_haitian_blip haitian_contact_blip
	REMOVE_BLIP haitian_contact_blip

// OTHER BLIPS
ADD_SPRITE_BLIP_FOR_CONTACT_POINT -853.0 -302.0 10.0 the_assasin_blip assasin_contact_blip
	REMOVE_BLIP assasin_contact_blip

// PROPERTY BLIPS
ADD_SPRITE_BLIP_FOR_CONTACT_POINT bankjobX bankjobY bankjobZ the_bankjob_blip bankjob_contact_blip
	REMOVE_BLIP bankjob_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT philX philY philZ the_phil_blip phil_contact_blip
	REMOVE_BLIP phil_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT pornX pornY pornZ the_porn_blip porn_contact_blip
	REMOVE_BLIP porn_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT baronX baronY baronZ the_protection_blip protect_contact_blip
	REMOVE_BLIP protect_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT counterX counterY counterZ the_counter_blip counter_contact_blip
//	REMOVE_BLIP counter_contact_blip // Practice SCM - keeping printworks blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT baronX baronY baronZ the_baron_blip baron_contact_blip
	REMOVE_BLIP baron_contact_blip 

ADD_SPRITE_BLIP_FOR_CONTACT_POINT sergioX sergioY sergioZ the_sergio_blip sergio_contact_blip
	//REMOVE_BLIP sergio_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT taxiwarX taxiwarY taxiwarZ the_taxiwar_blip taxiwar_contact_blip
	REMOVE_BLIP taxiwar_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT generalX generalY generalZ the_general_blip general_contact_blip
	REMOVE_BLIP general_contact_blip


// ************************************PROPERTY BUYING VARIABLES************************************

//BOAT YARD
VAR_INT boatbuy_blip
VAR_INT boatbuy_price
VAR_INT boatyard_revenue
VAR_INT boatyard_cash_pickup
VAR_INT boatyard_asset_acquired
boatbuy_price = 10000
boatyard_revenue = 2000
boatyard_asset_acquired	= 0

//ICECREAM FACTORY
VAR_INT icebuy_blip
VAR_INT icebuy_price
VAR_INT icecream_revenue
VAR_INT icecream_cash_pickup
VAR_INT icecream_asset_acquired
icebuy_price =  20000
icecream_revenue = 3000
icecream_asset_acquired	= 0

//STRIP CLUB
VAR_INT strpbuy_blip
VAR_INT strpbuy_price
VAR_INT strpbuy_pickup
VAR_INT strip_cash_pickup
VAR_INT stripclub_revenue
VAR_INT stripclub_asset_acquired
strpbuy_price = 30000
stripclub_revenue = 4000
stripclub_asset_acquired = 0

//TAXI FIRM
VAR_INT taxibuy_blip
VAR_INT taxibuy_price
VAR_INT taxifirm_revenue
VAR_INT taxifirm_cash_pickup
VAR_INT taxifirm_asset_acquired
taxibuy_price = 40000
taxifirm_revenue = 5000
taxifirm_asset_acquired	= 0

//CAR SHOWROOM
VAR_INT carbuy_blip
VAR_INT carbuy_price
VAR_INT showroom_revenue
VAR_INT showroom_cash_pickup
VAR_INT showroom_asset_acquired
carbuy_price = 50000
showroom_revenue = 1500//per board $6000 all together
showroom_asset_acquired = 0

//FILM STUDIO
VAR_INT pornbuy_blip
VAR_INT pornbuy_price
VAR_INT porn_revenue
VAR_INT porn_cash_pickup
VAR_INT porn_asset_acquired
pornbuy_price = 60000
porn_revenue = 7000
porn_asset_acquired	= 0

//PRINTWORKS
VAR_INT print_works_blip
VAR_INT printworks_price
VAR_INT printworks_revenue
VAR_INT printworks_cash_pickup
printworks_price = 70000
printworks_revenue = 8000

//MALIBU CLUB
VAR_INT bankbuy_blip
VAR_INT bankbuy_price
VAR_INT malibu_revenue
VAR_INT malibu_cash_pickup
VAR_INT malibu_asset_acquired
bankbuy_price = 120000
malibu_revenue = 10000
malibu_asset_acquired = 0

//SKUMOLE TERRACE
VAR_INT skumbuy_blip
VAR_INT skumbuy_price
VAR_INT skumbuy_pickup
skumbuy_price = 1000

//3321 VICE POINT
VAR_INT vcptbuy_blip
VAR_INT vcptbuy_price
VAR_INT vcptbuy_pickup
vcptbuy_price = 2500

//1102 WASHINGTON STREET
VAR_INT washbuy_blip
VAR_INT washbuy_price
VAR_INT washbuy_pickup
washbuy_price = 3000

//LINKS VIEW APARTMENT - 1 GARAGE
VAR_INT lnkvbuy_blip
VAR_INT lnkvbuy_price
VAR_INT lnkvbuy_pickup
VAR_INT lnkvbuy_save_garage
lnkvbuy_price = 6000

//OCEAN HEIGHTS APARTMENT - 1 GARAGE
VAR_INT ochebuy_blip
VAR_INT ochebuy_price
VAR_INT ochebuy_pickup
VAR_INT ochebuy_save_garage
ochebuy_price = 7000

//ELSWANKO CASA	- 1 GARAGE
VAR_INT nbmnbuy_blip
VAR_INT nbmnbuy_price
VAR_INT nbmnbuy_pickup
VAR_INT nbmnbuy_save_garage
nbmnbuy_price = 8000

//HYMAN CONDO - 3 GARAGES & HELI PAD
VAR_INT hycobuy_blip
VAR_INT hycobuy_price
VAR_INT hycobuy_pickup
VAR_INT hycobuy_save_garage1
VAR_INT hycobuy_save_garage2
VAR_INT hycobuy_save_garage3
hycobuy_price = 14000

// WASTED HELP ICONS
VAR_INT wasted_help1
CREATE_PICKUP INFO PICKUP_ONCE 493.5 703.1 12.1 wasted_help1

VAR_INT wasted_help2
CREATE_PICKUP INFO PICKUP_ONCE -108.3 -974.4 10.4 wasted_help2

// BUSTED HELP ICONS
VAR_INT busted_help1
CREATE_PICKUP INFO PICKUP_ONCE 508.9 506.8 11.3 busted_help1

VAR_INT busted_help2
CREATE_PICKUP INFO PICKUP_ONCE 398.8 -469.7 11.7  busted_help2  

//PRINTWORKS PURCHASING PICKUP
VAR_INT print_works_pickup
CREATE_LOCKED_PROPERTY_PICKUP printbuyX printbuyY printbuyZ PRNT_NO print_works_pickup//You cannot buy the Print Works at this time, come back later.

//CAR SHOWROOM PURCHASING PICKUP
VAR_INT carbuy_pickup
CREATE_LOCKED_PROPERTY_PICKUP carbuyX carbuyY carbuyZ CAR_NO carbuy_pickup//You cannot buy the Car Showroom at this time, come back later.

//FILM STUDIO PURCHASING PICKUP
VAR_INT pornbuy_pickup
CREATE_LOCKED_PROPERTY_PICKUP pornbuyX pornbuyY pornbuyZ PORN_NO pornbuy_pickup//You cannot buy the Film Studio at this time, come back later.

//ICECREAM FACTORY PURCHASING PICKUP
VAR_INT icebuy_pickup
CREATE_LOCKED_PROPERTY_PICKUP icebuyX icebuyY icebuyZ ICE_NO icebuy_pickup//You cannot buy the Icecream Factory at this time, come back later.

//TAXI FIRM PURCHASING PICKUP
VAR_INT taxibuy_pickup
CREATE_LOCKED_PROPERTY_PICKUP taxibuyX taxibuyY taxibuyZ TAXI_NO taxibuy_pickup//You cannot buy the Taxi Company at this time, come back later.

//MALIBU CLUB PURCHASING PICKUP
VAR_INT bankbuy_pickup
CREATE_LOCKED_PROPERTY_PICKUP bankbuyX bankbuyY bankbuyZ BANK_NO bankbuy_pickup//You cannot buy The Malibu at this time, come back later.

//BOAT YARD PURCHASING PICKUP
VAR_INT boatbuy_pickup
CREATE_LOCKED_PROPERTY_PICKUP boatbuyX boatbuyY boatbuyZ BOAT_NO boatbuy_pickup//You cannot buy the Boat Yard at this time, come back later.

CREATE_LOCKED_PROPERTY_PICKUP strpbuyX strpbuyY strpbuyZ STRP_NO strpbuy_pickup//Press R3 to purchase the Car Showroom for $~1~ 

CREATE_FORSALE_PROPERTY_PICKUP nbmnbuyX nbmnbuyY nbmnbuyZ nbmnbuy_price NBMN_L nbmnbuy_pickup//Press L1 to purchase the Mansion for $~1~
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT nbmnbuyX nbmnbuyY nbmnbuyZ RADAR_SPRITE_PROPERTY nbmnbuy_blip 
REMOVE_BLIP nbmnbuy_blip

CREATE_FORSALE_PROPERTY_PICKUP lnkvbuyX lnkvbuyY lnkvbuyZ lnkvbuy_price LNKV_L lnkvbuy_pickup//Press L1 to purchase the Mansion for $~1~ 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT lnkvbuyX lnkvbuyY lnkvbuyZ RADAR_SPRITE_PROPERTY lnkvbuy_blip 
REMOVE_BLIP lnkvbuy_blip

CREATE_FORSALE_PROPERTY_PICKUP hycobuyX hycobuyY hycobuyZ hycobuy_price HYCO_L hycobuy_pickup//Press L1 to purchase the Mansion for $~1~ 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT hycobuyX hycobuyY hycobuyZ RADAR_SPRITE_PROPERTY hycobuy_blip 
REMOVE_BLIP hycobuy_blip

CREATE_FORSALE_PROPERTY_PICKUP ochebuyX ochebuyY ochebuyZ ochebuy_price OCHE_L ochebuy_pickup//Press L1 to purchase the Mansion for $~1~ 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT ochebuyX ochebuyY ochebuyZ RADAR_SPRITE_PROPERTY ochebuy_blip 
REMOVE_BLIP ochebuy_blip

CREATE_FORSALE_PROPERTY_PICKUP washbuyX washbuyY washbuyZ washbuy_price WASH_L washbuy_pickup//Press L1 to purchase the Mansion for $~1~ 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT washbuyX washbuyY washbuyZ RADAR_SPRITE_PROPERTY washbuy_blip 
REMOVE_BLIP washbuy_blip

CREATE_FORSALE_PROPERTY_PICKUP vcptbuyX vcptbuyY vcptbuyZ vcptbuy_price VCPT_L vcptbuy_pickup//Press L1 to purchase the Mansion for $~1~ 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT vcptbuyX vcptbuyY vcptbuyZ RADAR_SPRITE_PROPERTY vcptbuy_blip 
REMOVE_BLIP vcptbuy_blip

CREATE_FORSALE_PROPERTY_PICKUP skumbuyX skumbuyY skumbuyZ skumbuy_price SKUM_L skumbuy_pickup//Press L1 to purchase the Mansion for $~1~ 
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT skumbuyX skumbuyY skumbuyZ RADAR_SPRITE_PROPERTY skumbuy_blip 
REMOVE_BLIP skumbuy_blip


// **********************************************GARAGES******************************************

VAR_INT	respray_garage1 respray_garage2	respray_garage3	respray_garage4	respray_garage5
VAR_INT mansion_save_garage11
VAR_INT collect_all_cars1
VAR_INT garage_col4_dropoff	garage_col4_destination
VAR_INT bud3_garage bomb_garage
VAR_INT carbuy_save_garage1	carbuy_save_garage2	carbuy_save_garage3	carbuy_save_garage4

SET_GARAGE (-914.129, -1263.54, 10.706) (-907.137, -1246.626) (-906.3, -1266.9) (14.421) GARAGE_RESPRAY respray_garage1//docks sprayshop
SET_GARAGE (-1014.341 -857.732 6.325) (-1001.315 -857.732) (-1014.341 -841.532) (10.885) GARAGE_RESPRAY respray_garage2//carshowroom sprayshop
SET_ROTATING_GARAGE_DOOR respray_garage2
SET_GARAGE (-886.157 -115.158 9.992) (-882.699 -108.312) (-876.7 -119.83) (15.58) GARAGE_RESPRAY respray_garage3//haiti
SET_GARAGE (323.9 427.4 10.0) (326.3 434.5) (313.9 430.53) (15.7) GARAGE_RESPRAY respray_garage4//nbeachbt
SET_GARAGE (-7.55 -1253.77 9.322) (-7.55 -1261.2) (2.64 -1253.7)(14.4)  GARAGE_RESPRAY respray_garage5//ocean drive area

SET_GARAGE -1056.05 -469.668 10.053 -1054.074 -486.611 -1038.966 -467.675 14.753 GARAGE_MISSION garage_col4_dropoff
SET_ROTATING_GARAGE_DOOR garage_col4_dropoff

SET_GARAGE -823.448 -1488.083 10.852 -834.686 -1515.899 -842.0 -1481.0 16.165 GARAGE_FOR_SCRIPT_TO_OPEN_FOR_CAR garage_col4_destination
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE garage_col4_destination
SET_ROTATING_GARAGE_DOOR garage_col4_destination

SET_GARAGE (-966.016 -861.529 5.761)(-978.454 -861.529)(-966.016 -841.683)(11.273) GARAGE_MISSION collect_all_cars1 //Import/Export garage industrial
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE collect_all_cars1
SET_ROTATING_GARAGE_DOOR collect_all_cars1

SET_GARAGE (449.137 340.002 10.794)( 465.42 340.002)( 465.43 326.187) (14.7) GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE bud3_garage //copland bud3 garage
SET_ROTATING_GARAGE_DOOR bud3_garage

SET_GARAGE -1163.248 -1407.282 10.157 -1178.292 -1400.939 -1159.338 -1397.813 16.989 GARAGE_BOMBSHOP3 bomb_garage //Docks

SET_GARAGE ( 303.998, 400.718, 12.025 ) (298.697, 402.389 ) (301.281, 410.584 ) (16.044 ) GARAGE_MISSION lnkvbuy_save_garage
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE lnkvbuy_save_garage
SET_ROTATING_GARAGE_DOOR lnkvbuy_save_garage
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE lnkvbuy_save_garage 1

SET_GARAGE (-848.225, 1303.119, 10.421) (-853.657, 1318.901) (-836.832, 1307.033) (15.816) GARAGE_MISSION hycobuy_save_garage1
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE hycobuy_save_garage1
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE hycobuy_save_garage1 4

SET_GARAGE (-825.466, 1311.499, 10.537) (-828.66, 1320.791) (-817.159, 1314.355) (15.061) GARAGE_MISSION hycobuy_save_garage2
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE hycobuy_save_garage2
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE hycobuy_save_garage2 2

SET_GARAGE (-816.37, 1314.69, 10.582) (-808.09, 1317.46) (-819.54, 1324.01) (15.061) GARAGE_MISSION hycobuy_save_garage3
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE hycobuy_save_garage3
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE hycobuy_save_garage3 2

SET_GARAGE (27.143, -1483.954, 9.423) (22.611, -1483.384) (21.365, -1490.59) (12.994) GARAGE_MISSION ochebuy_save_garage
SET_ROTATING_GARAGE_DOOR ochebuy_save_garage
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE ochebuy_save_garage
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE ochebuy_save_garage 1

SET_GARAGE (450.136, 641.029, 10.112) (450.136, 635.726) (457.977, 641.029) (13.092) GARAGE_MISSION	nbmnbuy_save_garage
SET_ROTATING_GARAGE_DOOR nbmnbuy_save_garage
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE nbmnbuy_save_garage			
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE nbmnbuy_save_garage 1

SET_GARAGE (-981.654, -802.265, 6.325)(-991.127, -802.265)(-981.654, -821.865)(10.73) GARAGE_MISSION carbuy_save_garage1
SET_ROTATING_GARAGE_DOOR carbuy_save_garage1
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE carbuy_save_garage1
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE carbuy_save_garage1 2

SET_GARAGE (-992.416, -802.265,6.325)(-1001.889, -802.265)(-992.416, -821.865)(10.73) GARAGE_MISSION carbuy_save_garage2
SET_ROTATING_GARAGE_DOOR carbuy_save_garage2
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE carbuy_save_garage2
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE carbuy_save_garage2 2

SET_GARAGE (-1003.771, -802.265, 6.325)(-1013.244, -802.265)(-1003.771, -821.865)(10.73) GARAGE_MISSION	carbuy_save_garage3
SET_ROTATING_GARAGE_DOOR carbuy_save_garage3
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE carbuy_save_garage3
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE carbuy_save_garage3 2

SET_GARAGE (-1015.436, -802.265, 6.325)(-1024.909, -802.265)(-1015.436,-821.865)(10.73)	GARAGE_MISSION carbuy_save_garage4
SET_ROTATING_GARAGE_DOOR carbuy_save_garage4
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE carbuy_save_garage4
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE carbuy_save_garage4 2

SET_GARAGE (-362.12 -550.214 11.722) (-362.12 -539.484) (-353.12 -550.214) (15.16) GARAGE_MISSION mansion_save_garage11
SET_ROTATING_GARAGE_DOOR mansion_save_garage11
NO_SPECIAL_CAMERA_FOR_THIS_GARAGE mansion_save_garage11
SET_MAXIMUM_NUMBER_OF_CARS_IN_GARAGE mansion_save_garage11 2

// **********************************************OTHER BLIPS**************************************

VAR_FLOAT ind_saveX ind_saveY ind_saveZ
VAR_FLOAT com_saveX com_saveY com_saveZ
VAR_FLOAT sub_saveX sub_saveY sub_saveZ
VAR_FLOAT spray_taxi_indX spray_taxi_indY spray_taxi_indZ
VAR_FLOAT spray_taxi_comX spray_taxi_comY spray_taxi_comZ
VAR_FLOAT spray_taxi_subX spray_taxi_subY spray_taxi_subZ

ADD_SPRITE_BLIP_FOR_COORD spray_taxi_indX spray_taxi_indY spray_taxi_indZ RADAR_SPRITE_SPRAY spray_taxi
REMOVE_BLIP	spray_taxi

////////////////////////////////////////////////////////////////////////////////
//THE COLONELS YACHT////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
VAR_INT the_yacht
VAR_INT the_yacht2
VAR_INT the_yacht3
VAR_INT the_yacht4
VAR_INT the_yacht5
VAR_INT the_yacht6
VAR_INT the_yacht7
VAR_INT the_yacht8
VAR_INT the_path_spline

INITIALISE_OBJECT_PATH 0 90.0 the_path_spline //spath0.dat

CREATE_OBJECT_NO_OFFSET yt_main_body -375.499 -1322.31 9.81124 the_yacht
CREATE_OBJECT_NO_OFFSET yt_main_body2 -375.499 -1322.31 9.81124 the_yacht2
CREATE_OBJECT_NO_OFFSET yt_doors14 -375.499 -1322.31 9.81124 the_yacht4
CREATE_OBJECT_NO_OFFSET yt_tmp_boat -375.499 -1322.31 9.81124 the_yacht5
CREATE_OBJECT_NO_OFFSET lodmain_body -375.499 -1322.31 9.81124 the_yacht6

DONT_REMOVE_OBJECT the_yacht
DONT_REMOVE_OBJECT the_yacht2
DONT_REMOVE_OBJECT the_yacht4
DONT_REMOVE_OBJECT the_yacht5
DONT_REMOVE_OBJECT the_yacht6

START_OBJECT_ON_PATH the_yacht the_path_spline
START_OBJECT_ON_PATH the_yacht2 the_path_spline
START_OBJECT_ON_PATH the_yacht4 the_path_spline
START_OBJECT_ON_PATH the_yacht5 the_path_spline
START_OBJECT_ON_PATH the_yacht6 the_path_spline

SET_OBJECT_PATH_SPEED the_path_spline 0.0

WAIT 0 
WAIT 0

CREATE_OBJECT_NO_OFFSET yacht_chunk_kb the_yacht_x the_yacht_y the_yacht_z the_yacht8
DONT_REMOVE_OBJECT the_yacht8

CREATE_OBJECT_NO_OFFSET yt_gangplnk_tmp the_yacht_x the_yacht_y the_yacht_z the_yacht7
DONT_REMOVE_OBJECT the_yacht7

//LOAD_AND_LAUNCH_MISSION intro.sc // Practice SCM - don't launch intro. Tommy spawns in marker for practice SCM mission anyway

// ****************************************START_NEW_SCRIPTS******************************************
START_NEW_SCRIPT game_help_loop
START_NEW_SCRIPT army_base_loop
LAUNCH_MISSION hj.sc
LAUNCH_MISSION usj.sc
LAUNCH_MISSION ammu.sc
LAUNCH_MISSION security.sc
LAUNCH_MISSION interiors.sc
LAUNCH_MISSION mobile.sc
LAUNCH_MISSION cargen.sc
LAUNCH_MISSION pickups.sc
LAUNCH_MISSION junkfud.sc													  
LAUNCH_MISSION rampage.sc
LAUNCH_MISSION robbing.sc
LAUNCH_MISSION audio.sc

WAIT 0
	
IF IS_PLAYER_PLAYING player1
	FORCE_WEATHER_NOW WEATHER_SUNNY	
		
		IF NOT IS_CHAR_DEAD scplayer
			UNDRESS_CHAR scplayer player

			LOAD_ALL_MODELS_NOW

			IF NOT IS_CHAR_DEAD scplayer
				DRESS_CHAR scplayer
			ENDIF
		ENDIF
		
	DO_FADE 1000 FADE_IN

	IF IS_PLAYER_PLAYING player1
		SET_AREA_VISIBLE VIS_MAIN_MAP
		SET_PLAYER_CONTROL player1 on
	ENDIF

	RELEASE_WEATHER

	GOTO mission_start
ENDIF


{
//MAIN LOOP********************************************************************************************************
mission_start:
WAIT 1000
IF IS_PLAYER_PLAYING player1
	IF game_percentage < 100.0 
		GET_PROGRESS_PERCENTAGE game_percentage
	ELSE
		IF created_final_shirt = 0
			CREATE_CLOTHES_PICKUP -382.6 -585.9 25.3 12 final_clothes
			PRINT_HELP ( CUNTY ) //New clothes delivered to the Vercetti Estate! 
			INCREASE_PLAYER_MAX_HEALTH player1 50
			INCREASE_PLAYER_MAX_ARMOUR player1 50
			ADD_ARMOUR_TO_PLAYER Player1 200
			SET_PLAYER_HEALTH Player1 200
			WAIT 5000
			PRINT_HELP ( HELP61 ) // You now have limitless ammo and double health on all vehicles.!
			created_final_shirt = 1
		ENDIF
	ENDIF 	 
	IF robbed_every_shop = 15
		PLAYER_MADE_PROGRESS 1
		robbed_every_shop = -1
	ENDIF
ENDIF //IF IS_PLAYER_PLAYING player1
GOTO mission_start
}

// Practice SCM Mission Loop
VAR_INT selecting
{
practice_mission_loop:
SCRIPT_NAME PRACLP
practice_mission_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ANY_MEANS_3D player1 -1166.802124 -1304.314697 14.818102 25.0 25.0 25.0 FALSE
			IF flag_player_on_mission = 0
				IF selecting = 0
					LOAD_AND_LAUNCH_MISSION practice.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	GOTO practice_mission_loop_inner
}

// Back Alley Brawl
{
lawyer_mission2_loop:
SCRIPT_NAME LAW2
lawyer_mission2_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 -1179.881470 -1313.508911 14.773191 2.0 2.0 2.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					flag_player_on_mission = 1
					MAKE_PLAYER_SAFE_FOR_CUTSCENE player1
					GOSUB lawyer_script_cut
					GOSUB make_player_safe
					PRINT_BIG ( LAW_2 ) 15000 2 //"Lawyer mission 2"
					GOSUB get_fading_status
					LOAD_AND_LAUNCH_MISSION bab.sc
				ENDIF

			ENDIF
		ENDIF
	ENDIF
GOTO lawyer_mission2_loop_inner
}

// Guardian Angels
{
general_mission3_loop:
SCRIPT_NAME GEN3
general_mission3_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 -1155.757202 -1306.177368 14.877080 2.0 2.0 2.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					GOSUB general_script_cut
					GOSUB make_player_safe
					PRINT_BIG ( COL_3 ) 15000 2 //"general mission 3"
					GOSUB get_fading_status
				   	LOAD_AND_LAUNCH_MISSION ga.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
GOTO general_mission3_loop_inner
} 

// Phnom Penh '86
{
baron_mission2_loop:
SCRIPT_NAME	BAR2
baron_mission2_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 -1163.470215 -1317.255859 14.861924 2.0 2.0 2.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					GOSUB baron_script_cut
					GOSUB make_player_safe
					PRINT_BIG ( COK_2 ) 15000 2 //"baron mission 2"
					GOSUB get_fading_status
					LOAD_AND_LAUNCH_MISSION pp86.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
GOTO baron_mission2_loop_inner
}

// Demolition Man
{
serg_mission2_loop:
SCRIPT_NAME	SER2
sergio_mission2_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 sergioX sergioY sergioZ 1.5 2.0 2.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					GOSUB make_player_safe
					PRINT_BIG ( TEX_3 ) 15000 2 //"sergio mission 2"
					GOSUB get_fading_status
					LOAD_AND_LAUNCH_MISSION demoman.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
GOTO sergio_mission2_loop_inner
}

// The Shootist
{
bankjob_mission2_loop:
SCRIPT_NAME BANK2
bankjob_mission2_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 -1158.750488 -1314.221680 14.879073 2.0 2.0 2.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					OVERRIDE_NEXT_RESTART 248.076157 -227.237457 10.901572 270.0
					GOSUB bankjob_script_cut
					GOSUB make_player_safe
					PRINT_BIG ( BANK_2 ) 15000 2 //"bankjob mission 2"
					GOSUB get_fading_status
					LOAD_AND_LAUNCH_MISSION shootist.sc
					WAIT 1500
					LOAD_AND_LAUNCH_MISSION shootist.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
GOTO bankjob_mission2_loop_inner
}

// Bar Brawl
{
protect_mission2_loop:
SCRIPT_NAME	PRO2
protect_mission2_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 -1154.562988 -1300.136108 14.870331 2.0 2.0 2.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					GOSUB make_player_safe
					PRINT_BIG ( BUD_2 ) 15000 2 //"protect mission 2"
				    GOSUB get_fading_status
					LOAD_AND_LAUNCH_MISSION barbrawl.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
GOTO protect_mission2_loop_inner
}

// Hit the Courier
{
counter_mission2_loop:
SCRIPT_NAME	COU2
counter_mission2_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 counterX counterY counterZ 1.5 2.0 2.0 FALSE
		OR LOCATE_PLAYER_ON_FOOT_3D player1 -1168.798706 -1318.395386 14.837980 1.0 1.0 1.0 FALSE
		OR LOCATE_PLAYER_ON_FOOT_3D player1 -1171.019165 -1317.085205 14.824290 1.0 1.0 1.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					GOSUB counter_script_cut
					GOSUB make_player_safe
					PRINT_BIG ( CNT_2 ) 15000 2 //"counter mission 2"
					GOSUB get_fading_status
					LOAD_AND_LAUNCH_MISSION htc.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
GOTO counter_mission2_loop_inner
}

// Cap the Collector
{
finale_mission1_loop:
SCRIPT_NAME	FIN1
finale_mission1_loop_inner:
WAIT mission_trigger_wait_time
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 -1152.782349 -1296.682373 14.871946 2.0 2.0 2.0 FALSE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					selecting = 0
					SET_PLAYER_COORDINATES player1 -1063.208862 -283.142822 11.088032
					GOSUB counter_script_cut
					GOSUB make_player_safe
					PRINT_BIG ( CAP_1 ) 15000 2 //"finale mission 1"
					GOSUB get_fading_status
					LOAD_AND_LAUNCH_MISSION ctc.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
GOTO finale_mission1_loop_inner
}


// ************************************************************************************************************
// ********************************************GANG MEMBERS***************************************************

{

gang_member_loop:

SCRIPT_NAME GANGMEM

VAR_INT gang_member gang_member_1 
LVAR_INT gang_member_2 gang_member_3
LVAR_INT gang_member_flag gang_member_1_flag gang_member_2_flag gang_member_3_flag
LVAR_INT gang_member_blip gang_member_1_blip gang_member_2_blip	gang_member_3_blip	  
LVAR_FLOAT gang_member_start_x gang_member_start_y gang_member_start_z gang_member_start_heading 
LVAR_FLOAT gang_member_locate_x gang_member_locate_y
VAR_FLOAT percentage_of_game_complete
gang_member_1 = -1
gang_member_2 = -1
gang_member_3 = -1
gang_member_1_blip = 0
gang_member_2_blip = 0
gang_member_3_blip = 0
gang_member_1_flag = 0
gang_member_2_flag = 0
gang_member_3_flag = 0
percentage_of_game_complete = 0.0

gang_member_loop_inner:

	WAIT mission_trigger_wait_time

	GET_PROGRESS_PERCENTAGE percentage_of_game_complete
	
	WHILE percentage_of_game_complete = 100.0
		
		WAIT 100
		
		gang_member		   	 = gang_member_1		  
		gang_member_flag	 = gang_member_1_flag	  
		gang_member_blip	 = gang_member_1_blip	  
		gang_member_start_x  = -403.7998 
		gang_member_start_y  = -558.5297 
		gang_member_start_z  = 17.9388 
		gang_member_start_heading = 170.5255 
		gang_member_locate_x = -403.7675
		gang_member_locate_y = -559.4329
		GOSUB do_each_gang_member
		gang_member_1	   = gang_member		  
		gang_member_1_flag = gang_member_flag	  
		gang_member_1_blip = gang_member_blip	  
		   
		WAIT 100
		
		gang_member		   	 = gang_member_2		  
		gang_member_flag	 = gang_member_2_flag	  
		gang_member_blip	 = gang_member_2_blip	  
		gang_member_start_x  = -401.1314 
		gang_member_start_y  = -565.7331 
		gang_member_start_z  = 18.5404 
		gang_member_start_heading = 100.8949 
		gang_member_locate_x = -402.1638
		gang_member_locate_y = -565.7678
		GOSUB do_each_gang_member
		gang_member_2	   = gang_member		  
		gang_member_2_flag = gang_member_flag	  
		gang_member_2_blip = gang_member_blip	  

		WAIT 100
		
		gang_member		   	 = gang_member_3		  
		gang_member_flag	 = gang_member_3_flag	  
		gang_member_blip	 = gang_member_3_blip	  
		gang_member_start_x  = -404.3082 
		gang_member_start_y  = -561.7204 
		gang_member_start_z  = 17.9388 
		gang_member_start_heading = 7.0257 
		gang_member_locate_x = -404.3812
		gang_member_locate_y = -561.1186
		GOSUB do_each_gang_member
		gang_member_3	   = gang_member		  
		gang_member_3_flag = gang_member_flag	  
		gang_member_3_blip = gang_member_blip	  

	ENDWHILE

GOTO gang_member_loop_inner

do_each_gang_member:
IF flag_player_on_mission = 0
	IF IS_PLAYER_PLAYING player1
		IF IS_PLAYER_IN_INFO_ZONE player1 gang1
			IF IS_SCORE_GREATER player1 6000
				IF gang_member_flag = 0
					IF HAS_MODEL_LOADED PGA
						IF HAS_MODEL_LOADED	UZI
							IF NOT IS_POINT_ON_SCREEN gang_member_start_x gang_member_start_y gang_member_start_z 1.0
								CREATE_CHAR	PEDTYPE_CIVMALE	PGA gang_member_start_x gang_member_start_y gang_member_start_z gang_member
								SET_CHAR_HEADING gang_member gang_member_start_heading
								SET_CHAR_AS_PLAYER_FRIEND gang_member player1 TRUE
								SET_CHAR_PERSONALITY gang_member PEDSTAT_TOUGH_GUY
								CLEAR_CHAR_THREAT_SEARCH gang_member
								SET_CHAR_NEVER_TARGETTED gang_member TRUE
								GIVE_WEAPON_TO_CHAR	gang_member WEAPONTYPE_UZI 9999
								SET_CHAR_WAIT_STATE	gang_member WAITSTATE_SIT_IDLE 60000000
								DONT_REMOVE_CHAR gang_member
								SET_CHAR_RUNNING gang_member TRUE
								SET_CHAR_HEALTH	gang_member 250
								SET_CHAR_ACCURACY gang_member 80
								SET_CHAR_CAN_BE_SHOT_IN_VEHICLE gang_member FALSE
								SET_CHAR_SUFFERS_CRITICAL_HITS gang_member FALSE
								ADD_SPHERE gang_member_locate_x gang_member_locate_y gang_member_start_z 0.75 gang_member_blip
								gang_member_flag = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF gang_member_flag < 2
				MARK_CHAR_AS_NO_LONGER_NEEDED gang_member
				gang_member_flag = 0
				REMOVE_SPHERE gang_member_blip
			ENDIF
		ENDIF
		IF gang_member_flag = 1
			IF NOT IS_CHAR_DEAD	gang_member
				IF LOCATE_PLAYER_ANY_MEANS_3D player1 gang_member_locate_x gang_member_locate_y gang_member_start_z 1.0 1.0 2.0 0
					IF IS_SCORE_GREATER player1 2000
						ADD_SCORE player1 -2000
					ENDIF
					SET_PLAYER_AS_LEADER gang_member player1
					CLEAR_CHAR_WAIT_STATE gang_member
					REMOVE_SPHERE gang_member_blip
					gang_member_flag = 2
				ENDIF
			ELSE
				GOSUB clear_up_gang_member
			ENDIF
		ENDIF
		IF gang_member_flag = 2
			IF NOT IS_CHAR_DEAD	gang_member
				IF LOCATE_PLAYER_ANY_MEANS_CHAR_2D player1 gang_member 100.0 100.0 0
					IF NOT IS_CHAR_IN_PLAYERS_GROUP	gang_member player1
						SET_PLAYER_AS_LEADER gang_member player1
					ENDIF
				ELSE
					GOSUB clear_up_gang_member
				ENDIF
			ELSE
				GOSUB clear_up_gang_member
			ENDIF
		ENDIF
	ELSE
		GOSUB clear_up_gang_member
	ENDIF
ELSE
	GOSUB clear_up_gang_member
ENDIF
RETURN

clear_up_gang_member:
IF NOT gang_member_flag = 0
	MARK_CHAR_AS_NO_LONGER_NEEDED gang_member
	IF IS_PLAYER_PLAYING player1
		IF NOT IS_PLAYER_IN_INFO_ZONE player1 gang1
			gang_member_flag = 0
		ENDIF
	ELSE
		gang_member_flag = 0
	ENDIF
	REMOVE_SPHERE gang_member_blip
ENDIF
RETURN

}

// ************************************************************************************************************
// ********************************************ARMY BASE STUFF*************************************************
{
army_base_loop:

SCRIPT_NAME ARMYBAS

LVAR_INT setup_army_base_stuff
setup_army_base_stuff = 0

army_base_loop_inner:

	WAIT mission_trigger_wait_time

	IF IS_PLAYER_PLAYING PLAYER1
		IF IS_PLAYER_IN_INFO_ZONE PLAYER1 AIRPORT
		OR IS_PLAYER_IN_INFO_ZONE PLAYER1 ARMYBAS
			IF setup_army_base_stuff = 0
				SET_GANG_PED_MODELS GANG_STREET ARMY ARMY
				SET_THREAT_FOR_PED_TYPE PEDTYPE_GANG_STREET THREAT_PLAYER1
				SET_GANG_WEAPONS GANG_STREET WEAPONTYPE_M4 WEAPONTYPE_M4
				SET_GANG_ATTACK_PLAYER_WITH_COPS GANG_STREET TRUE
				REQUEST_MODEL ARMY
				setup_army_base_stuff = 1
			ENDIF
			IF setup_army_base_stuff = 1
				IF IS_PLAYER_IN_MODEL player1 HUNTER
				OR IS_PLAYER_WEARING player1 player6
				OR IS_PLAYER_IN_AREA_2D player1 -1682.2396 -179.9125 -1600.4482 -150.8878 0
					CLEAR_THREAT_FOR_PED_TYPE PEDTYPE_GANG_STREET THREAT_PLAYER1
				ELSE
					SET_THREAT_FOR_PED_TYPE PEDTYPE_GANG_STREET THREAT_PLAYER1
				ENDIF
			ENDIF
		ELSE
			IF setup_army_base_stuff = 1
				SET_GANG_PED_MODELS GANG_STREET SGA SGB
				CLEAR_THREAT_FOR_PED_TYPE PEDTYPE_GANG_STREET THREAT_PLAYER1
				SET_GANG_WEAPONS GANG_STREET WEAPONTYPE_PISTOL WEAPONTYPE_UNARMED
				SET_GANG_ATTACK_PLAYER_WITH_COPS GANG_STREET FALSE
				WAIT 0
				WAIT 0
				WAIT 0
				IF flag_player_on_army_ped_mission = 0
					MARK_MODEL_AS_NO_LONGER_NEEDED ARMY
				ENDIF
				setup_army_base_stuff = 0
			ENDIF
		ENDIF
	ENDIF

GOTO army_base_loop_inner
}

// ************************************************************************************************************
// *******************************************Shooting Range Missions**************************************************

// Shoot Mission 1
{
shoot_range_loop:



SCRIPT_NAME SHOOT

IF IS_PLAYER_PLAYING player1
	WHILE LOCATE_PLAYER_ON_FOOT_3D Player1 -665.63 1231.863 10.1 10.0 10.0 3.0 FALSE
		WAIT 0
		IF IS_PLAYER_PLAYING player1
		ENDIF
	ENDWHILE
ENDIF

shoot_range_loop_inner:

WAIT 0
	
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_PLAYER_ON_FOOT_3D player1 -665.63 1231.863 10.1 1.0 1.0 2.0 TRUE
			IF flag_player_on_mission = 0
				IF CAN_PLAYER_START_MISSION player1
					flag_player_on_mission = 1
					SET_PLAYER_CONTROL player1 OFF
					SET_FADING_COLOUR 0 0 0
					DO_FADE 1500 FADE_OUT
					GOSUB get_fading_status
					//LOAD_AND_LAUNCH_MISSION range.sc
					IF IS_PLAYER_PLAYING player1
						WHILE LOCATE_PLAYER_ON_FOOT_3D Player1 -665.63 1231.863 10.1 10.0 10.0 3.0 FALSE
							WAIT 0
							IF IS_PLAYER_PLAYING player1
							ENDIF
						ENDWHILE
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO shoot_range_loop_inner

}

// ************************************************************************************************************
// ************************************************************************************************************
// *******************************************END OF MISSIONS**************************************************
// ************************************************************************************************************
// ************************************************************************************************************

// **********************************PRINTERS SAVE LOOP*********************************************
{
printers_save_loop:
//	Should be called before main loop
	SCRIPT_NAME PSAVE8
printers_save_loop_inner:
	WAIT 250
	IF IS_PLAYER_PLAYING player1
		IF IS_PLAYER_IN_ZONE player1 HAVANA
			IF CAN_PLAYER_START_MISSION player1
				IF flag_player_on_mission = 0
					IF remove_printers_pickup = 0
						CREATE_PICKUP pickupsave PICKUP_ONCE -1059.6 -282.2 11.2 printers_pickup	
						remove_printers_pickup = 1
					ENDIF
					IF HAS_PICKUP_BEEN_COLLECTED printers_pickup
						GOSUB save_the_game
						IF IS_PLAYER_PLAYING player1
							REMOVE_PICKUP printers_pickup
							CREATE_PICKUP pickupsave PICKUP_ONCE -1059.6 -282.2 11.2 printers_pickup 
							DO_FADE 1000 FADE_IN
							CLEAR_AREA -1056.7 -279.7 10.6 1.0 TRUE
							SET_PLAYER_COORDINATES player1 -1056.7 -279.7 10.6
							SET_PLAYER_HEADING player1 273.0
						ENDIF
						GOSUB save_the_game2	
					ENDIF
				ELSE
					IF remove_printers_pickup = 1
						REMOVE_PICKUP printers_pickup
						remove_printers_pickup = 0
					ENDIF
				ENDIF
			ENDIF //  IF CAN_PLAYER_START_MISSION player1
		ENDIF // IF IS_PLAYER_IN_ZONE player1 
	ENDIF // IF IS_PLAYER_PLAYING player1

	GOTO printers_save_loop_inner
}

// ****************************************HELP TEXT*************************************************
{
game_help_loop:
	SCRIPT_NAME	HELP
game_help_loop_inner:
	WAIT 0
	IF IS_PLAYER_PLAYING player1
		//IF flag_intro_passed = 1
			/*IF print_first_help = 0
				WAIT 500
				GET_CONTROLLER_MODE controlmode
				IF controlmode = 2
					PRINT_HELP ( HELP26 )  //ENTER A CAR	L1
				ELSE
					PRINT_HELP ( HELP21 ) //ENTER A CAR	triangle
				ENDIF
								
				WAIT 5000
	
				PRINT_HELP ( HELP42 )  // "This is the ~h~radar~w~. Use it to navigate the city. Follow the ~q~pink blip~w~ to find the hotel." );

				ADD_BLIP_FOR_CONTACT_POINT 240.4 -1280.2 10.0 goto_hotel_contact_blip

				FLASH_HUD_OBJECT HUD_FLASH_RADAR
					WAIT 3000
				FLASH_HUD_OBJECT -1
				TIMERB = 0
				WAIT 3000
				print_first_help = 1
			ELSE
			
				IF stop_in_marker = 0
					IF LOCATE_PLAYER_ANY_MEANS_3D player1 240.4 -1280.2 10.0 25.0 25.0 5.0 FALSE 
						PRINT_HELP ( HELP44 )  // "Stop in the ~q~pink marker."
						WAIT 3000
						stop_in_marker = 1
					ENDIF
				ENDIF

				IF IS_PLAYER_PLAYING player1
					IF car_help_played = 0
						IF IS_PLAYER_IN_ANY_CAR player1
							WAIT 1000

							GET_CONTROLLER_MODE controlmode
							IF controlmode = 3
								PRINT_HELP ( HELP4_D ) //Push the right analog stick up to accelerate.
							ELSE
								PRINT_HELP ( HELP4_A ) //Press the / button to accelerate
							ENDIF

							WAIT 4000

							GET_CONTROLLER_MODE controlmode
							IF controlmode = 1
							OR controlmode = 3
								PRINT_HELP ( HELP36 ) //Push left or right on the left analog stick to steer the vehicle.
							ELSE
								PRINT_HELP ( HELP35 ) //Press the left or right directional buttons, or the left analog stick, to steer the vehicle.
							ENDIF

							WAIT 4000
							
							GET_CONTROLLER_MODE controlmode
							IF controlmode = 3
								PRINT_HELP ( HELP5_D ) //Pull the right analog stick back to brake, or to reverse if the vehicle has stopped.
							ELSE
								PRINT_HELP ( HELP5_A ) //Press the * button to brake, or to reverse if the vehicle has stopped
							ENDIF
							
							WAIT 4000

							GET_CONTROLLER_MODE controlmode
							IF controlmode = 0
							OR controlmode = 1
								PRINT_HELP ( HELP6_A ) //Press the R1 button to apply the vehicle's handbrake
							ENDIF
							IF controlmode = 2
								PRINT_HELP ( HELP6_C ) //Press the " button to apply the vehicle's handbrake
							ENDIF
							IF controlmode = 3
								PRINT_HELP ( HELP6_D ) //Press the L1 button to apply the vehicle's handbrake
							ENDIF
														
							WAIT 4000
							
							car_help_played = 1
						ENDIF
					ELSE
					
						IF Storm_Warning = 1	
							IF IS_PLAYER_PLAYING player1
								IF IS_PLAYER_ON_ANY_BIKE player1
									IF bike_help = 0
										WAIT 2000
										IF IS_PLAYER_PLAYING player1
											IF controlmode = 0
												PRINT_HELP ( HELP27 ) //Press the ~h~up or down directional buttons~w~, or the ~h~left analog stick~w~, to shift your weight on a bike.
											ELSE
												PRINT_HELP ( HELP28 ) //Press ~h~up~w~ or ~h~down~w~ on the ~h~left analog stick~w~ to shift your weight on a bike.
											ENDIF
										ENDIF
										bike_help = 1
									ENDIF
								ENDIF

								IF IS_PLAYER_PLAYING player1
									IF IS_PLAYER_IN_ANY_CAR player1
										IF drive_by_help = 0
											IF IS_CURRENT_PLAYER_WEAPON Player1 WEAPONTYPE_TEC9
											OR IS_CURRENT_PLAYER_WEAPON Player1 WEAPONTYPE_UZI
											OR IS_CURRENT_PLAYER_WEAPON Player1 WEAPONTYPE_SILENCED_INGRAM 
											OR IS_CURRENT_PLAYER_WEAPON Player1 WEAPONTYPE_MP5

												PRINT_HELP ( HELP31 ) // To do a drive-by, first look left or right using the L2 button or the R2 button.
											   												
												WAIT 5000

												GET_CONTROLLER_MODE controlmode
												IF controlmode = 3
													PRINT_HELP ( HELP33 ) //Then fire using the R1 button.
												ELSE
													PRINT_HELP ( HELP32 ) //Then fire using the | button.
												ENDIF

												WAIT 5000

												PRINT_HELP ( HELP34 ) // "You must have a sub machine gun	to perform a drive-by."
											   												
												drive_by_help = 1
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					ENDIF //car_help_played = 0
						

				ENDIF //IS_PLAYER_PLAYING
			ENDIF //print_first_help = 0
*/
			IF IS_PLAYER_PLAYING player1
				IF view_of_ocean_view = 0
					IF LOCATE_STOPPED_PLAYER_IN_CAR_3D player1 240.4 -1280.2 10.0 4.0 4.0 3.0 FALSE
					OR LOCATE_PLAYER_ON_FOOT_3D player1 240.4 -1280.2 10.0 4.0 4.0 3.0 FALSE
						SET_PLAYER_CONTROL player1 OFF
						SET_FIXED_CAMERA_POSITION 264.334 -1311.160 10.297 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 263.577 -1310.574 10.589 JUMP_CUT
						WAIT 1000
						PRINT_HELP ( HELP16	) //Walk through the front door of the ~h~Ocean View~w~ Hotel to enter the building.
						WAIT 3000

						IF IS_PLAYER_PLAYING player1
							SET_PLAYER_CONTROL player1 ON
						ENDIF
						RESTORE_CAMERA_JUMPCUT
						SET_CAMERA_BEHIND_PLAYER
						/*REMOVE_BLIP	goto_hotel_contact_blip
						IF flag_hotel_mission1_passed = 0
							ADD_BLIP_FOR_CONTACT_POINT hotelX hotelY hotelZ goto_hotel_contact_blip
						ENDIF*/
						view_of_ocean_view = 1
					ENDIF
				ENDIF
			ENDIF
/*
			IF print_save_game_help = 0
				IF flag_player_on_mission = 0
					IF flag_player_in_hotel = 1
						IF IS_PLAYER_PLAYING player1
						  
							IF LOCATE_PLAYER_ANY_MEANS_3D player1 225.0 -1277.3 11.5 2.5 2.5 3.0 FALSE
								REMOVE_BLIP	goto_hotel_contact_blip
								IF flag_hotel_mission1_passed = 0
									ADD_BLIP_FOR_CONTACT_POINT hotelX hotelY hotelZ goto_hotel_contact_blip
								ENDIF
								SET_PLAYER_CONTROL player1 OFF
								SET_FIXED_CAMERA_POSITION 223.301 -1277.655 13.822 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 222.578 -1277.013 13.565 JUMP_CUT
								PRINT_HELP ( S_PROMP ) //Save game
								TIMERA = 0

								WHILE NOT TIMERA > 1000
									WAIT 0
										
									IF IS_PLAYER_PLAYING player1
										SET_PLAYER_CONTROL player1 OFF
									ENDIF

								ENDWHILE

								TIMERA = 0

								WHILE NOT TIMERA > 2000
									WAIT 0
										
									IF IS_PLAYER_PLAYING player1
										SET_PLAYER_CONTROL player1 OFF
									ENDIF

									IF IS_BUTTON_PRESSED PAD1 cross
										GOTO end_of_save_help
									ENDIF

								ENDWHILE

								SET_INTERPOLATION_PARAMETERS 0.0 1500
								SET_FIXED_CAMERA_POSITION 223.301 -1277.655 13.822 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 222.563 -1278.279 13.565 INTERPOLATION
								TIMERA = 0

								WHILE NOT TIMERA > 1500
									WAIT 0
										
									IF IS_BUTTON_PRESSED PAD1 cross
										GOTO end_of_save_help
									ENDIF

								ENDWHILE

								PRINT_HELP ( HELP19 ) //Continue the game
								TIMERA = 0

								WHILE NOT TIMERA > 4000
									WAIT 0	

									IF IS_BUTTON_PRESSED PAD1 cross
										GOTO end_of_save_help
									ENDIF

								ENDWHILE

								end_of_save_help:

								IF IS_PLAYER_PLAYING player1
									SET_PLAYER_CONTROL player1 ON
								ENDIF
								RESTORE_CAMERA_JUMPCUT
								SET_CAMERA_BEHIND_PLAYER
								print_save_game_help = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF flag_hotel_mission1_passed = 1
				IF print_help_for_radar = 0
					ADD_SPRITE_BLIP_FOR_CONTACT_POINT 219.8 -1273.5 11.0 RADAR_SPRITE_SAVEHOUSE hotel_contact_blip	//SAVE POINT HOTEL

					CHANGE_BLIP_DISPLAY hotel_contact_blip BLIP_ONLY

					
					IF IS_PLAYER_PLAYING player1
						SET_PLAYER_CONTROL player1 OFF
					ENDIF
					GOSUB get_fading_status	  
					IF IS_PLAYER_PLAYING player1
						SET_PLAYER_CONTROL player1 ON
					ENDIF
			 		PRINT_HELP ( HELP14 ) //Radar help
					FLASH_HUD_OBJECT HUD_FLASH_RADAR
					WAIT 2000
					
					FLASH_HUD_OBJECT -1
					
					print_help_for_radar = 1
				ENDIF
			ENDIF		   

			IF print_help_for_radar = 1
				IF car_help_played = 1
					IF camera_help_played = 0
						WAIT 4000
						
						GET_CONTROLLER_MODE controlmode
						IF controlmode = 1
							PRINT_HELP ( HELP57 )	// Press the ~h~up or down ~w~directional buttons to change the camera mode.
						ELSE
							PRINT_HELP ( HELP56 )	// Press the ~h~select~w~ button to change camera modes.
						ENDIF

						camera_help_played = 1
					ENDIF
				ENDIF
			ENDIF
*/
			IF drive_by_help = 1
			AND bike_help = 1
 			AND print_help_for_radar = 1
			AND camera_help_played = 1
			AND police_bribe_help = 1
			AND wanted_help = 1

				IF wasted_help = 1
					REMOVE_PICKUP wasted_help1
					REMOVE_PICKUP wasted_help2
					REMOVE_PICKUP busted_help1
					REMOVE_PICKUP busted_help2
					TERMINATE_THIS_SCRIPT
				ENDIF
						
			ENDIF

			IF flag_baron_mission2_passed = 1
				REMOVE_PICKUP wasted_help1
				REMOVE_PICKUP wasted_help2
				REMOVE_PICKUP busted_help1
				REMOVE_PICKUP busted_help2
				WAIT 3000
				PRINT_HELP ( BRID_OP )
				TERMINATE_THIS_SCRIPT
			ENDIF 

			IF wasted_help = 0

				IF HAS_PICKUP_BEEN_COLLECTED wasted_help1
				OR HAS_PICKUP_BEEN_COLLECTED wasted_help2

					IF IS_PLAYER_PLAYING player1
						REMOVE_PICKUP wasted_help1
						REMOVE_PICKUP wasted_help2
					   	PRINT_HELP ( HEAL_B ) // You have been wasted
						WAIT 5000
						PRINT_HELP ( HEAL_C )
						WAIT 5000
						PRINT_HELP ( HEAL_E )
						wasted_help = 1
					ENDIF

				ENDIF

			ENDIF
			
			IF wanted_help = 0

				IF HAS_PICKUP_BEEN_COLLECTED busted_help1
				OR HAS_PICKUP_BEEN_COLLECTED busted_help2

					IF IS_PLAYER_PLAYING player1
						REMOVE_PICKUP busted_help1
						REMOVE_PICKUP busted_help2
						PRINT_HELP ( WANT_G )
						WAIT 5000
						PRINT_HELP ( WANT_H )
						WAIT 5000
						PRINT_HELP ( WANT_I )
						WAIT 5000
						PRINT_HELP ( WANT_A ) // You have a wanted level
						WAIT 5000
						PRINT_HELP ( WANT_B )
						WAIT 5000
						PRINT_HELP ( WANT_F )
						wanted_help = 1
					ENDIF

				ENDIF
					
			ENDIF
			
			IF police_bribe_help = 0

				IF HAS_PICKUP_BEEN_COLLECTED beach_bribe1
				OR HAS_PICKUP_BEEN_COLLECTED beach_bribe2
				OR HAS_PICKUP_BEEN_COLLECTED beach_bribe3
				OR HAS_PICKUP_BEEN_COLLECTED beach_bribe4
				OR HAS_PICKUP_BEEN_COLLECTED beach_bribe5
				OR HAS_PICKUP_BEEN_COLLECTED beach_bribe6

					IF IS_PLAYER_PLAYING player1
						PRINT_HELP ( BRIBE1 )
						police_bribe_help = 1
					ENDIF

				ENDIF

				IF HAS_PICKUP_BEEN_COLLECTED porn_bribe1
					
					IF IS_PLAYER_PLAYING player1
						PRINT_HELP ( BRIBE1 )
						police_bribe_help = 1
					ENDIF

				ENDIF
				
				IF HAS_PICKUP_BEEN_COLLECTED main_bribe1
				OR HAS_PICKUP_BEEN_COLLECTED main_bribe2
				OR HAS_PICKUP_BEEN_COLLECTED main_bribe3
				OR HAS_PICKUP_BEEN_COLLECTED main_bribe4
				OR HAS_PICKUP_BEEN_COLLECTED main_bribe5
				OR HAS_PICKUP_BEEN_COLLECTED main_bribe6

					IF IS_PLAYER_PLAYING player1
						PRINT_HELP ( BRIBE1 )
						police_bribe_help = 1
					ENDIF

				ENDIF
				
			ENDIF

			IF wanted_star_help = 0

				IF WANTED_STARS_ARE_FLASHING
					WAIT 5000

				   	IF IS_PLAYER_PLAYING player1
						PRINT_HELP ( WANT_L )
						wanted_star_help = 1
					ENDIF

				ENDIF
												
			ENDIF

			IF car_help_played = 1
				IF Storm_Warning = 0
				   IF IS_PLAYER_PLAYING player1					
					   IF IS_PLAYER_IN_ANY_CAR player1
					   		PLAY_ANNOUNCEMENT 0
							PRINT_HELP ( BRID_CL ) //"Storm Warning all bridges to the mainland is closed"
							Storm_Warning = 1
					   ENDIF
					ENDIF
				ENDIF
			ENDIF	
			
		//ENDIF //flag_intro_passed
	ENDIF // IF IS_PLAYER_PLAYING player1

	GOTO game_help_loop_inner

}



make_player_safe:

	IF IS_PLAYER_PLAYING player1
		flag_player_on_mission = 1
		
		MAKE_PLAYER_SAFE_FOR_CUTSCENE player1

		SET_FADING_COLOUR 0 0 0

		DO_FADE 1500 FADE_OUT

		SWITCH_STREAMING OFF
	ENDIF


RETURN


get_fading_status:

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	IF IS_PLAYER_PLAYING player1
		SET_CHAR_OBJ_NO_OBJ scplayer
	ENDIF

RETURN


lawyer_script_cut:

	IF IS_PLAYER_PLAYING player1	
		flag_player_on_mission = 1
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION 91.925 -818.489 20.000 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 92.866 -818.736 19.769 JUMP_CUT
		CLEAR_AREA 119.2 -826.9 10.0 1.0 TRUE
		SET_PLAYER_COORDINATES player1 119.4 -827.2 9.7
		SET_PLAYER_HEADING player1 230.0
		SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer 123.3 -829.9
	ENDIF

	WAIT 1000

RETURN

baron_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -381.923 -473.339 48.904 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -381.898 -474.247 48.486 JUMP_CUT //View of Mansion front

	WAIT 1000

RETURN

general_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -229.438 -1364.204 12.607 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -230.424 -1364.068 12.511 JUMP_CUT //View of boat
	SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -251.9 -1360.8

	WAIT 1000

RETURN

porn_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -52.624 939.244 10.011 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -53.538 938.966 10.306 JUMP_CUT //View of porn studio
	SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -76.3 931.3

	WAIT 1000

RETURN
	
kent_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION 499.781 -106.921 12.057 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 499.357 -106.019 12.139 JUMP_CUT //View of Malibu
	//SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer 487.8 -74.3
	SET_CHAR_COORDINATES scplayer 482.2 -69.9 9.9
	WAIT 1000

RETURN

bankjob_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION 487.475 -68.248 11.147 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 486.500 -68.023 11.150 JUMP_CUT //View of Inside Malibu
	SET_CHAR_HEADING scplayer 62.0

	WAIT 1000

RETURN

phil_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -1094.531 332.318 14.799 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1095.238 332.993 14.591 JUMP_CUT //View of Phil's caravan
	SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -1102.7 343.4

	WAIT 1000

RETURN

	
counter_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -1039.093 -293.828 27.810 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1039.811 -293.283 27.377 JUMP_CUT //View of Print works
	SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -1070.6 -278.9

	WAIT 1000

RETURN

biker_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -609.208 660.793 14.520 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -608.458 660.156 14.339 JUMP_CUT //View of Biker Bar
	SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -597.4 651.6

	WAIT 1000

RETURN

rock_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -870.590 1155.257 10.356 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -871.349 1155.864 10.592 JUMP_CUT //View of Recording studio
	SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -883.1 1159.2

	WAIT 1000

RETURN

cuban_script_cut:
	
	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -1179.528 -605.912 12.220 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -1178.774 -606.569 12.223 JUMP_CUT //View of Cuban Cafe
	//SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -1169.9 -608.9
	SET_CHAR_COORDINATES scplayer -1166.3 -634.4 10.6 

	WAIT 1000

RETURN

haitian_script_cut:

	flag_player_on_mission = 1
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -969.554 139.730 10.058 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -968.921 140.503 10.031 JUMP_CUT //View of Haitian shack
	SET_CHAR_OBJ_GOTO_COORD_ON_FOOT scplayer -962.6 146.1

	WAIT 1000

RETURN

taxiwar_script_cut:

	flag_player_on_mission = 1
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -994.919 210.472 15.758 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -995.722 210.020 15.370 JUMP_CUT

RETURN


save_the_game:

	flag_player_on_mission = 1
	SET_PLAYER_CONTROL player1 Off
		
	ACTIVATE_SAVE_MENU //THE GAME SAVES/RE-LOADS HERE!!!!!
							
	WAIT 0
								
	WHILE NOT HAS_SAVE_GAME_FINISHED
		WAIT 0

	ENDWHILE

	SET_FADING_COLOUR 0 0 0

	DO_FADE 1000 FADE_OUT

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_HEALTH Player1 200
		SET_PLAYER_CONTROL player1 OFF
	ENDIF

RETURN

save_the_game2:

	IF IS_PLAYER_PLAYING player1
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
	ENDIF

	WAIT 500

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_CONTROL player1 on
		flag_player_on_mission = 0
	ENDIF

RETURN


