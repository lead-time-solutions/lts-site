
build: clean garden
	lein cljsbuild once min

clean:
	lein clean

figwheel:
	lein figwheel

garden:
	lein garden once

garden-watch:
	lein garden auto

publish: build
	aws s3 cp resources/public/ s3://www.tim-metcalf.com/ --recursive --region ap-southeast-2
