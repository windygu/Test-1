echo %time% >> log.txt
ffmpeg -i JianCe20mbps.avi -f h264 -vcodec mpeg4 -s 1920x1280 -r 25 test.mp4
echo %time% >> log.txt
pause