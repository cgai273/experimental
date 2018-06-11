import numpy as np


class Environment:
    def __init__(self, arm_count, arm_var):
        self.arms_ = np.random.normal(loc=0, scale=1.0, size=arm_count)
        self.original_arms_ = self.arms_.copy()
        self.arm_var = arm_var

    def evolve(self):
        self.arms_ += np.random.normal(loc=0, scale=0.1, size=self.arms_.size)
        self.arm_var += 0.01

    def compute_reward(self, action):
        reward_per_arm = np.random.normal(loc=self.arms_, scale=self.arm_var)
        return reward_per_arm[action]

    def reset(self):
        self.arms_ = self.original_arms_.copy()

if __name__ == "__main__":
    env = Environment(10, 1)
    print(env.arms_)
    env.evolve()
    print(env.arms_)
    print(env.compute_reward(3))