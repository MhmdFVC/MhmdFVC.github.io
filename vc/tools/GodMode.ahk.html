#IfWinActive GTA: Vice City ;This script only works if the game is the active window.

;Author: YBurakD
;Disclaimer: This script does not have the best practices of programming.

;Only bikes' handling can be improved as of now.
g::makeBikeGod()

;This can be used for any vehicle. Use only once per vehicle.
h::makeVehicleHeavy()

;Revert operations. Can be commented out if unnecessary.
k::revertGodMode()
l::revertHeavyProperty()

makeBikeGod(){
	;Change handling multiplier and bindings to your preferences.
	handlingMultiplier := 2.0
	base:= 0x947D30
	offset1:= 0x3a8
	offset2:= 0x454
	
	memory := MemoryOpenFromName("gta-vc.exe")
	addr1 := DecToHex(MemoryRead(memory,base))
	addr2 := DecToHex(MemoryRead(memory,addr1,"int",4,offset1))
	MemoryWrite(memory,addr2,handlingMultiplier,"float",4,offset2)
	
	MemoryClose(memory)
}
makeVehicleHeavy(){
	base:= 0x947D30
	offset1:= 0x3a8
	memory := MemoryOpenFromName("gta-vc.exe")
	
	addr1 := DecToHex(MemoryRead(memory,base))
	addr2 := DecToHex(MemoryRead(memory,addr1,"int",4,offset1))

	;1/3
	offset2:= 0xB8
	val := 3*MemoryRead(memory,addr2,"float",4,offset2)
	MemoryWrite(memory,addr2,val,"float",4,offset2)
	
	;2/3
	offset2:= 0xBC
	val := 3*MemoryRead(memory,addr2,"float",4,offset2)
	MemoryWrite(memory,addr2,val,"float",4,offset2)
	
	;3/3
	offset2:= 0x11A
	val := MemoryRead(memory,addr2,"byte",1,offset2)
	MemoryWrite(memory,addr2,val+1,"byte",1,offset2)
	
	MemoryClose(memory)

}

revertGodMode(){
	base:= 0x947D30
	offset1:= 0x3a8
	offset2:= 0x454
	
	memory := MemoryOpenFromName("gta-vc.exe")
	addr1 := DecToHex(MemoryRead(memory,base))
	addr2 := DecToHex(MemoryRead(memory,addr1,"int",4,offset1))
	MemoryWrite(memory,addr2,1.0,"float",4,offset2)
	
	MemoryClose(memory)
}
revertHeavyProperty(){
	base:= 0x947D30
	offset1:= 0x3a8
	memory := MemoryOpenFromName("gta-vc.exe")
	
	addr1 := DecToHex(MemoryRead(memory,base))
	addr2 := DecToHex(MemoryRead(memory,addr1,"int",4,offset1))

	;1/3
	offset2:= 0xB8
	val := MemoryRead(memory,addr2,"float",4,offset2)/3
	MemoryWrite(memory,addr2,val,"float",4,offset2)
	
	;2/3
	offset2:= 0xBC
	val := MemoryRead(memory,addr2,"float",4,offset2)/3
	MemoryWrite(memory,addr2,val,"float",4,offset2)
	
	;3/3
	offset2:= 0x11A
	val := MemoryRead(memory,addr2,"byte",1,offset2)
	MemoryWrite(memory,addr2,val-1,"byte",1,offset2)
	
	MemoryClose(memory)

}
DecToHex(Value)
{
    SetFormat IntegerFast, Hex
    Value += 0
    Value .= "" ;required due to 'fast' mode
    SetFormat IntegerFast, D
    Return Value
}

MemoryOpenFromPID(PID, Privilege=0x1F0FFF)
{
	HWND := DllCall("OpenProcess", "Uint", Privilege, "int", 0, "int", PID)
	return HWND
}

MemoryOpenFromName(Name, Privilege=0x1F0FFF)
{
	Process, Exist, %Name%
	PID := ErrorLevel
	Return MemoryOpenFromPID(PID, Privilege)
}

MemoryClose(hwnd)
{
	return DllCall("CloseHandle", "int", hwnd)
}

MemoryWrite(hwnd, address, writevalue, datatype="int", length=4, offset=0)
{
	VarSetCapacity(finalvalue, length, 0)
	NumPut(writevalue, finalvalue, 0, datatype)
	return DllCall("WriteProcessMemory", "Uint", hwnd, "Uint", address+offset, "Uint", &finalvalue, "Uint", length, "Uint", 0)
}

MemoryRead(hwnd, address, datatype="int", length=4, offset=0)
{
	VarSetCapacity(readvalue,length, 0)
	DllCall("ReadProcessMemory","Uint",hwnd,"Uint",address+offset,"Str",readvalue,"Uint",length,"Uint *",0)
	finalvalue := NumGet(readvalue,0,datatype)
	return finalvalue
}
