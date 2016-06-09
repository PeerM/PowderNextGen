simulation step:
- sort particles into cells
- update cell
  - count particles in cell
  - calc pressure from particle count
  - update flow rate in each direction, using current pressure and last cell pressures
- update particle
  - look at cell flow rates: chose a random one weight by relative magnitude
  - take into account gravity
  - combine gravity and chosen flow vector
  - update velocity
  - move by velocity
    - don't move outside the play are

cell:
- flow rate in each direction
- particles ?
- pressure

particle:
- position :Vector2D
- velocity

world:
- particles
- cells