#IfWinActive GTA: Vice City ;This script only works if the game is the active window

KYFCTimer := 0x00822DB4 ;Timer address

lanceWait := 0	;Waiting time for Lance to spawn after key press, in seconds. You can change this value.
sonnyWait := 0	;Waiting time for Sonny to spawn after key press, in seconds. You can change this value.

f4::spawnLance(KYFCTimer,lanceWait)	;Key bind to spawn Lance. You can change this bind.
f5::spawnSonny(KYFCTimer,sonnyWait)	;Key bind to spawn Sonny. You can change this bind.

spawnLance(KYFCTimer,lanceWait)
{
	lanceSpawnTime := 0x008230F0
	memory := MemoryOpenFromName("gta-vc.exe")
	currentTime := MemoryRead(memory,KYFCTimer)
	waitTime := currentTime + (1000*lanceWait)
	MemoryWrite(memory,lanceSpawnTime,waitTime)
	MemoryClose(memory)
}

spawnSonny(KYFCTimer,sonnyWait)
{
	sonnySpawnTime := 0x00823108
	memory := MemoryOpenFromName("gta-vc.exe")
	currentTime := MemoryRead(memory,KYFCTimer)
	waitTime :=  currentTime + (1000*sonnyWait)
	MemoryWrite(memory,sonnySpawnTime,waitTime)
	MemoryClose(memory)
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