
default: all

all: 
	mono .paket/paket.bootstrapper.exe
	mono .paket/paket.exe install
	cp packages/Newtonsoft.Json/lib/net40/Newtonsoft.Json.dll ./
	fsharpc --standalone -r:packages/Newtonsoft.Json/lib/net40/Newtonsoft.Json.dll kmeans.fs main.fs

run:
	mono main.exe
