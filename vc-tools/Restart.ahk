#IfWinActive ahk_class Grand theft auto 3
0::
Process, Close, gta-vc.exe
FileDelete, C:\Users\benel\OneDrive\Dokument\GTA Vice City User Files\replay.rep
Run, gta-vc.exe, C:\Program Files (x86)\Rockstar Games\Grand Theft Auto Vice City JP
Sleep 800
Send {LWin down}
Sleep 66
Send {LWin up}
Sleep 500
Send {LWin down}
Sleep 66
Send {LWin up}
Send {RButton down}
Sleep 66
Send {RButton up}
return
#IfWinActive
