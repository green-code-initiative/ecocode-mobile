REM == Define CodeNarc version
set codenarc_version=3.5.0

REM == Build CodeNarc
cd codenarc-converter/CodeNarc
call ./gradlew build -x test

REM == Deploy to local repository
cd ..
mvn -B install:install-file -Dfile=CodeNarc/build/libs/CodeNarc-%codenarc_version%.jar -DgroupId=org.codenarc -DartifactId=CodeNarc -Dversion=%codenarc_version% -Dpackaging=jar
