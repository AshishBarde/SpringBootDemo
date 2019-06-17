::set /p admin_version=
::echo admin_version %admin_version%

::set /p commmonWS_version=
::echo commmonWS_version %commmonWS_version%

::set /p employer_version=
::echo employer_version %employer_version%

::set /p employee_version=
::echo employee_version %employee_version%

::set /p reports_version=
::echo reports_version %reports_version%

::call awsECRSetup.bat

::aws ecr get-login --no-include-email --region us-east-1>awslogin.txt
::for /f "delims=" %%x in (awslogin.txt) do set dockerlogin=%%x  
::%dockerlogin%

::cd D:\SpringBenefitWorkspace\SpringBenefitWS
::echo inside D:\SpringBenefitWorkspace\SpringBenefitWS

::cd common-jpa
::call common-jpa-deployment.bat

::cd ..\commons
::call commons-deployment.bat

::cd ..\admin
::echo Building admin
::call admin-deployment.bat %admin_version%
::echo Pushing admin image
::docker push 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/admin:%admin_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/admin:%admin_version%

::cd ..\commonWS
::echo Building commonWS
::call common-deployment.bat %commmonWS_version%
::echo Pushing commonWS image
::docker push 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/common:%commmonWS_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/common:%commmonWS_version%

::cd ..\employer
::echo Building employer
::call employer-deployment.bat %employer_version%
::echo Pushing employer image
::docker push 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/employer:%employer_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/employer:%employer_version%

::cd ..\employee
::echo Building employee
::call employee-deployment.bat %employee_version%
::echo Pushing employee image
::docker push 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/employee:%employee_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/employee:%employee_version%

::cd ..\reports
::echo Building reports
::call reports-deployment.bat %reports_version%
::echo Pushing reports image
::docker push 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/reports:%reports_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/reports:%reports_version%


::cd ..
::del awslogin.txt

::echo Pushed below images
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/admin:%admin_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/common:%commmonWS_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/employer:%employer_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/employee:%employee_version%
::echo 974689566756.dkr.ecr.us-east-1.amazonaws.com/springbenefit/reports:%reports_version%
::pause