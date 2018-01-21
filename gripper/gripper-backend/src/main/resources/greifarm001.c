/*! \file   greifarm.c
    \brief  A simple rover demo
    \author Markus L. Noga <markus@noga.de>

    a simple rover that evades obstacles.
    assumes motors on A,C, touch sensors on port 1,3
 */

/*
 *  The contents of this file are subject to the Mozilla Public License
 *  Version 1.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an "AS IS"
 *  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 *  License for the specific language governing rights and limitations
 *  under the License.
 *
 *  The Original Code is legOS code, released October 17, 1999.
 *
 *  The Initial Developer of the Original Code is Markus L. Noga.
 *  Portions created by Markus L. Noga are Copyright (C) 1999
 *  Markus L. Noga. All Rights Reserved.
 *
 *  Contributor(s): Markus L. Noga <markus@noga.de>
 */


#include <config.h>
#if defined(CONF_DSENSOR) && defined(CONF_DMOTOR)

#include <conio.h>
#include <unistd.h>

#include <dsensor.h>
#include <dmotor.h>

#include <sys/lcd.h>
#include <tm.h>

///////////////////////////////////////////////////////////////////////////////
//
// Functions
//
///////////////////////////////////////////////////////////////////////////////

wakeup_t sensor_press_wakeup(wakeup_t data);

/*
 * sba:
 * A: rotation around z-axis [-1: turn ~20° with clock at fwd; 1: turn ~20° against clock at max rev] [2sec]
 * B: open/close hand [-1: closed at max fwd; 1: open at max rev] [3.5sec]
 * C: lift arm [-1: arm down at nearly max rev; 1: arm up at nearly max fwd] [10sec]
 */

int main(int argc, char *argv[]) {
	int dir=0;

	while (!shutdown_requested()) {
		motor_a_speed(2*MAX_SPEED/3);		// go!
		motor_b_speed(2*MAX_SPEED/3);
		motor_c_speed(2*MAX_SPEED/3);

		//motor_a_dir(fwd);
		//motor_b_dir(rev);
		motor_c_dir(fwd);

		cputs("c=1");


		msleep(10000);

		motor_a_speed(0);
		motor_b_speed(0);
		motor_c_speed(0);

		cputs("arm lifted");
		break;

	}

	return 0;
}

wakeup_t sensor_press_wakeup(wakeup_t data) {
	lcd_refresh();
	return (SENSOR_1<0xf000) || (SENSOR_3<0xf000);
}

#else
#warning rover.c requires CONF_DMOTOR and CONF_DSENSOR
#warning rover demo will do nothing
int main(int argc, char *argv[]) {
	return 0;
}
#endif // CONF_DSENSOR, CONF_DMOTOR
