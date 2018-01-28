#!/bin/bash
curl -XPUT -H "Content-Type: text/turtle" -d "<> <https://w3id.org/saref#hasState> \"opened\"^^<http://www.w3.org/2001/XMLSchema#string> ." http://km.aifb.kit.edu/services/step-iot/gripper/claw/
